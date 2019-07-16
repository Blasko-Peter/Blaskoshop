package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.AddressDao;
import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.implementation.AddressDaoMem;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.model.Address;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/address"})
public class AddressController extends HttpServlet {

    String message;
    AddressDao ad = AddressDaoMem.getInstance();
    CartDao cd = CartDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addressSearch = req.getParameter("addresssearch");
        String[] actualAddressSerch = addressSearch.split(",");
        if(actualAddressSerch.length < 2){
            resp.sendRedirect("/order");
        } else {
            message = "";
            HttpSession session = req.getSession();
            Cart actualShopCart = (Cart) session.getAttribute("shopcart");
            Map<Product, Integer> products = cd.mapConverter(actualShopCart.getShopcart());
            List<Address> addresses = ad.getAllAddressToUser((Integer) session.getAttribute("userid"));
            int addressesnumber = addresses.size();
            List<Address> actualAddress = ad.getAddressToAddress(actualAddressSerch[1]);
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

}
