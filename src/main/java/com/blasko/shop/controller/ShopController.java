package com.blasko.shop.controller;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.dao.implementation.ProductDaoMem;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = {"/shop"})
public class ShopController extends HttpServlet {

    CartDao cd = CartDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart actualShopCart = (Cart) session.getAttribute("shopcart");
        String itemId = req.getParameter("id");
        Product product = pd.find(Integer.parseInt(itemId));
        if(actualShopCart.getShopcart().containsKey(product)){
            int newNumber = actualShopCart.getShopcart().get(product) + 1;
            actualShopCart.getShopcart().put(product, newNumber);
        } else {
            actualShopCart.getShopcart().put(product, 1);
        }
        cd.updateCart(actualShopCart);
        Cart newCart = cd.findActive((Integer) session.getAttribute("userid")).get(0);
        session.setAttribute("shopcart", newCart);
    }

}
