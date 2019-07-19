package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.AddressDao;
import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.UserDao;
import com.blasko.shop.dao.implementation.AddressDaoMem;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.dao.implementation.UserDaoMem;
import com.blasko.shop.model.Address;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    AddressDao ad = AddressDaoMem.getInstance();
    UserDao ud = UserDaoMem.getInstance();
    CartDao cd = CartDaoMem.getInstance();
    private String message = "";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userid") == null){
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("messgae", message);
            engine.process("product/order.html", context, resp.getWriter());
        } else {
            message = "";
            Cart actualShopCart = (Cart) session.getAttribute("shopcart");
            Map<Product, Integer> products = cd.mapConverter(actualShopCart.getShopcart());
            List<Address> addresses = ad.getAllAddressToUser((Integer) session.getAttribute("userid"));
            int addressesnumber = addresses.size();
            List<Address> actualAddress = new ArrayList<>();
            int actualAddressNumber = actualAddress.size();
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("products", products);
            context.setVariable("totalprice", cd.getTotalPrice(products));
            context.setVariable("message", message);
            context.setVariable("addressesnumber", addressesnumber);
            context.setVariable("addresses", addresses);
            context.setVariable("actualAddress", actualAddress);
            context.setVariable("actualAddressNumber", actualAddressNumber);
            engine.process("product/order.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userid");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");
        String postalCode = req.getParameter("postalCode");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String phoneNumber = req.getParameter("phoneNumber");
        if(firstName.equals("") || lastName.equals("") || address.equals("") || postalCode.equals("") || city.equals("") || country.equals("") || phoneNumber.equals("")){
            message = "Give all data, please!";
            Cart actualShopCart = (Cart) session.getAttribute("shopcart");
            Map<Product, Integer> products = cd.mapConverter(actualShopCart.getShopcart());
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("products", products);
            context.setVariable("totalprice", cd.getTotalPrice(products));
            context.setVariable("message", message);
            engine.process("product/order.html", context, resp.getWriter());
        } else {
            List<Address> addresses = ad.getAllAddressToUser(userId);
            int actualAddressId = 0;
            int index = ad.checkAddresses(addresses, firstName, lastName, address, postalCode, city, country, phoneNumber);
            if(index == 0){
                User newUser = ud.find(userId).get(0);
                Address newAddress = new Address(firstName, lastName, address, postalCode, city, country, phoneNumber, newUser);
                ad.add(newAddress);
                List<Address> newAddresses = ad.getAllAddressToUser(userId);
                actualAddressId = ad.checkAddresses(newAddresses, firstName, lastName, address, postalCode, city, country, phoneNumber);
            } else {
                actualAddressId = index;
            }
            Cart actualShopCart = (Cart) session.getAttribute("shopcart");
            int actualShopCartId = actualShopCart.getId();
            cd.closeCartById(actualShopCartId, actualAddressId);
            Map<Integer, Integer> shopcart = new HashMap<>();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String historydate = sdf.format(timestamp);
            Cart newcart = new Cart(userId, "active", historydate, shopcart, 1, 0);
            cd.add(newcart);
            resp.sendRedirect("/end");
        }
    }

}
