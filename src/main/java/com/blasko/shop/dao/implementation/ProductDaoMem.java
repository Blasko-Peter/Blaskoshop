package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import com.blasko.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoMem implements ProductDao {

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();
    SupplierDao sd = SupplierDaoMem.getInstance();

    private List<Product> data = null;
    private static ProductDaoMem instance = null;

    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        int productId = product.getId();
        String productName = product.getName();
        String productDescription = product.getDescription();
        float productPrice = product.getPrice();
        String productCurrency = product.getCurrency();
        int productCategory = product.getProductcategory().getId();
        int productSupplier = product.getSupplier().getId();
        String productActive = product.getActive();
        String productImage = product.getImage();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO products (id, name, description, price, currency, productcategory_id, supplier_id, active, image) values('"+productId+"','"+productName+"','"+productDescription+"','"+productPrice+"','"+productCurrency+"','"+productCategory+"','"+productSupplier+"','"+productActive+"','"+productImage+"')");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(int id, String image) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("UPDATE products SET image = '" + image + "' WHERE id = " + id + ";");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Product find(int id) {
        Product product = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE id = " + id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("currency"), pcd.find(rs.getInt("productcategory_id")), sd.find(rs.getInt("supplier_id")), rs.getString("active"), rs.getString("image"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("currency"), pcd.find(rs.getInt("productcategory_id")), sd.find(rs.getInt("supplier_id")), rs.getString("active"), rs.getString("image"));
                data.add(newProduct);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        data = new ArrayList<>();
        int supplier_id = supplier.getId();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE supplier_id = " + supplier_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("currency"), pcd.find(rs.getInt("productcategory_id")), sd.find(rs.getInt("supplier_id")), rs.getString("active"), rs.getString("image"));
                data.add(newProduct);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        data = new ArrayList<>();
        int productcategory_id = productCategory.getId();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE productcategory_id = " + productcategory_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("currency"), pcd.find(rs.getInt("productcategory_id")), sd.find(rs.getInt("supplier_id")), rs.getString("active"), rs.getString("image"));
                data.add(newProduct);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        data = new ArrayList<>();
        int productcategory_id = productCategory.getId();
        int supplier_id = supplier.getId();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM products WHERE productcategory_id = " + productcategory_id + " AND  supplier_id = " + supplier_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product newProduct = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getString("currency"), pcd.find(rs.getInt("productcategory_id")), sd.find(rs.getInt("supplier_id")), rs.getString("active"), rs.getString("image"));
                data.add(newProduct);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

}
