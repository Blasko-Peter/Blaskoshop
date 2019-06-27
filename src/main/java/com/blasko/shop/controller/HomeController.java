package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.implementation.ProductCategoryDaoMem;
import com.blasko.shop.dao.implementation.ProductDaoMem;
import com.blasko.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends HttpServlet {

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("recipient", "World");
        context.setVariable("category", pcd.find(1));
        context.setVariable("products", pd.getBy(pcd.find(1)));
        engine.process("product/index.html", context, resp.getWriter());
    }

}
