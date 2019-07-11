package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.dao.implementation.ProductDaoMem;
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
import java.util.Map;

@WebServlet (urlPatterns = {"/shopcart"})
public class ShopcartController extends HttpServlet {

    CartDao cd = CartDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userid") == null){
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            engine.process("product/shopcart.html", context, resp.getWriter());
        } else {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            engine.process("product/shopcart.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart actualShopCart = (Cart) session.getAttribute("shopcart");
        String itemId = req.getParameter("id");
        String number = req.getParameter("number");
        Product product = pd.find(Integer.parseInt(itemId));
        if(Integer.parseInt(number) > 0){
            int newNumber = Integer.parseInt(number);
            actualShopCart.getShopcart().put(product, newNumber);
        } else {
            actualShopCart.getShopcart().remove(product);
        }
        cd.updateCart(actualShopCart);
        Cart newCart = cd.findActive((Integer) session.getAttribute("userid")).get(0);
        session.setAttribute("shopcart", newCart);
    }

}
