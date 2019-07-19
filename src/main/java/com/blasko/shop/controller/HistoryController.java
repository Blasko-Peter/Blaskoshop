package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.HistoryDao;
import com.blasko.shop.dao.implementation.CartDaoMem;
import com.blasko.shop.dao.implementation.HistoryDaoMem;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.History;
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

@WebServlet(urlPatterns = {"/history"})
public class HistoryController extends HttpServlet {

    List<Cart> carts;
    List<History> histories;
    CartDao cd = CartDaoMem.getInstance();
    HistoryDao hd = HistoryDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userid") == null){
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            engine.process("product/history.html", context, resp.getWriter());
        } else {
            carts = cd.findAll((Integer) session.getAttribute("userid"));
            histories = hd.histories(carts);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("histories", histories);
            engine.process("product/history.html", context, resp.getWriter());
        }
    }

}
