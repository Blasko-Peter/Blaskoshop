package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;

import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.dao.implementation.SupplierDaoMem;
import com.blasko.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/supplier"})
public class SupplierController extends HttpServlet {

    SupplierDao sd = SupplierDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/supplier.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String suppliername = req.getParameter("suppliername");
        String supplierdescription = req.getParameter("supplierdescription");
        Supplier newSupplier = new Supplier(suppliername, supplierdescription);
        sd.add(newSupplier);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/supplier.html", context, resp.getWriter());
    }

}
