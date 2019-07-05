package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.UserDao;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.dao.implementation.UserDaoMem;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet (urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private String message = "";
    private String login = "";
    UserDao ud = UserDaoMem.getInstance();
    CartDao cd = CartDaoMem.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("username", login);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("message", message);
        engine.process("product/login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<User> user = ud.find(email);
        if(user.size() > 0 && ud.checkPassword(password, user.get(0).getPassword())){
            login = email;
            HttpSession session = req.getSession();
            session.setAttribute("username", login);
            session.setAttribute("userid", user.get(0).getId());
            List<Cart> usercart = cd.findActive(user.get(0).getId());
            if(usercart.size() == 0){
                Map<Integer, Integer> shopcart = new HashMap<>();
                Cart newcart = new Cart(user.get(0).getId(), "active", "2019-07-05", shopcart);
                cd.add(newcart);
            }
            resp.sendRedirect("/");
        } else {
            message = "Invalid email or password!";
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("message", message);
            engine.process("product/login.html", context, resp.getWriter());
        }
    }

}
