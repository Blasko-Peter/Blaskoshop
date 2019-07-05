package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.*;
import com.blasko.shop.dao.implementation.*;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import com.blasko.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends HttpServlet {

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();
    SupplierDao sd = SupplierDaoMem.getInstance();
    CartDao cd = CartDaoMem.getInstance();
    UserDao ud = UserDaoMem.getInstance();

    private String categorySearch = "Choose...";
    private String supplierSearch = "Choose...";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userid") == null){
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("categories", pcd.getAll());
            context.setVariable("suppliers", sd.getAll());
            context.setVariable("products", getActualProducts());
            engine.process("product/index.html", context, resp.getWriter());
        } else {
            Cart actualShopCart = cd.findActive((Integer) session.getAttribute("userid")).get(0);
            session.setAttribute("shopcart", actualShopCart);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("categories", pcd.getAll());
            context.setVariable("suppliers", sd.getAll());
            context.setVariable("products", getActualProducts());
            context.setVariable("shopcartitems", cd.countItems((Integer) session.getAttribute("userid")));
            engine.process("product/index.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categorySearch = req.getParameter("categorysearch");
        supplierSearch = req.getParameter("suppliersearch");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", pcd.getAll());
        context.setVariable("suppliers", sd.getAll());
        context.setVariable("products", getActualProducts());
        engine.process("product/index.html", context, resp.getWriter());
    }

    private List<Product> getActualProducts(){
        List<Product> actualProduct = new ArrayList<>();
        if(categorySearch.equals("Choose...") && supplierSearch.equals("Choose...")){
            actualProduct = pd.getAll();
        } else if(categorySearch.equals("Choose...") && !supplierSearch.equals("Choose...")){
            Supplier supplier = sd.find(supplierSearch);
            actualProduct = pd.getBy(supplier);
        } else if(!categorySearch.equals("Choose...") && supplierSearch.equals("Choose...")){
            ProductCategory productCategory = pcd.find(categorySearch);
            actualProduct = pd.getBy(productCategory);
        } else {
            ProductCategory productCategory = pcd.find(categorySearch);
            Supplier supplier = sd.find(supplierSearch);
            actualProduct = pd.getBy(productCategory, supplier);
        }
        return actualProduct;
    }

}
