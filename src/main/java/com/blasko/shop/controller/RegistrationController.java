package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.UserDao;
import com.blasko.shop.dao.implementation.UserDaoMem;
import com.blasko.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    private String message = "";
    UserDao ud = UserDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("message", message);
        engine.process("product/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(ud.checkEmail(email) && ud.checkPassword(password)){
            if(ud.find(email).size() == 0){
                String hash = ud.hashedPassword(password);
                User newUser = new User(email, hash);
                ud.add(newUser);
                message = "Registration was success!";
            } else {
                message = "This email is already registrated!";
            }
        } else {
            message = "Invalid email or password!";
        }
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("message", message);
        engine.process("product/registration.html", context, resp.getWriter());
    }

}
