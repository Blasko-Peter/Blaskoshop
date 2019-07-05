package com.blasko.shop.controller;

import com.blasko.shop.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String itemId = req.getParameter("id");
        System.out.println(itemId);
        Cart actualShopCart = (Cart) session.getAttribute("shopcart");
        System.out.println(actualShopCart.getId());
        System.out.println(actualShopCart.getShopcart());
        if(actualShopCart.getShopcart().containsKey(Integer.parseInt(itemId))){
            int newNumber = actualShopCart.getShopcart().get(Integer.parseInt(itemId)) + 1;
            actualShopCart.getShopcart().put(Integer.parseInt(itemId), newNumber);
        } else {
            actualShopCart.getShopcart().put(Integer.parseInt(itemId), 1);
        }
        System.out.println(actualShopCart.getShopcart());
    }

}
