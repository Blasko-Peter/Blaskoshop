package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.implementation.CartDaoMem;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart actualShopCart = (Cart) session.getAttribute("shopcart");
        Map<Product, Integer> products = cd.mapConverter(actualShopCart.getShopcart());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", products);
        engine.process("product/shopcart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart actualShopCart = (Cart) session.getAttribute("shopcart");
        String itemId = req.getParameter("id");
        String number = req.getParameter("number");
        if(Integer.parseInt(number) > 0){
            int newNumber = Integer.parseInt(number);
            actualShopCart.getShopcart().put(Integer.parseInt(itemId), newNumber);
        } else {
            actualShopCart.getShopcart().remove(Integer.parseInt(itemId));
        }
        cd.updateCart(actualShopCart);
        Cart newCart = cd.findActive((Integer) session.getAttribute("userid")).get(0);
        session.setAttribute("shopcart", newCart);
    }

}
