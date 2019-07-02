package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.dao.implementation.ProductCategoryDaoMem;
import com.blasko.shop.dao.implementation.ProductDaoMem;
import com.blasko.shop.dao.implementation.SupplierDaoMem;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import com.blasko.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = {"/admin/product"})
public class ProductController extends HttpServlet {

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();
    SupplierDao sd = SupplierDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();

    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String productId = null;
        HttpSession session = req.getSession();
        session.setAttribute("productid", productId);
        context.setVariable("categories", pcd.getAll());
        context.setVariable("suppliers", sd.getAll());
        context.setVariable("message", message);
        engine.process("product/product.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String productname = req.getParameter("productname");
            String productdescription = req.getParameter("productdescription");
            float productprice = Float.parseFloat(req.getParameter("productprice"));
            String productcurrency = req.getParameter("productcurrency");
            ProductCategory productCategory = pcd.find(req.getParameter("productcategory"));
            Supplier supplier = sd.find(req.getParameter("productsupplier"));
            String active = "acive";
            String image = "product_0.jpg";
            Product newProduct = new Product(productname, productdescription, productprice, productcurrency, productCategory, supplier, active, image);
            pd.add(newProduct);
            String productId = String.valueOf(pd.getAll().size() + 1);
            HttpSession session = req.getSession();
            session.setAttribute("productid", productId);
            message = "";
            resp.sendRedirect("/admin/product/image");
        } catch (NumberFormatException ex) {
            message = "Give all data, please!";
            resp.sendRedirect("/admin/product");
        }
    }

}
