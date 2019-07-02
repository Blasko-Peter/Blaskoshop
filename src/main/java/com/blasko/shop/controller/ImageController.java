package com.blasko.shop.controller;

import com.blasko.shop.config.TemplateEngineUtil;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.implementation.ProductDaoMem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/product/image"})
public class ImageController extends HttpServlet {

    ProductDao pd = ProductDaoMem.getInstance();
    private String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("message", message);
        engine.process("product/image.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int productId = Integer.parseInt(String.valueOf(session.getAttribute("productid")));
        String image = "product_0.jpg";
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> items = sf.parseRequest(req);
            for(FileItem item : items){
                item.write(new File("C://Users/user/Documents/Git/Blaskoshop/src/main/webapp/static/img/" + item.getName()));
                image = item.getName();
            }
            pd.update(productId, image);
            message = "";
            resp.sendRedirect("/admin/product");
        } catch (
                FileUploadException e) {
            message = "File not found! Check it again, please!";
            resp.sendRedirect("/admin/product/image");
        } catch (Exception e) {
            message = "Upload was failed!";
            resp.sendRedirect("/admin/product/image");
        }
    }

}
