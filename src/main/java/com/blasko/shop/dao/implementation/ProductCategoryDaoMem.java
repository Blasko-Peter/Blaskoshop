package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    private ProductCategoryDaoMem() {

    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        int categoryId = category.getId();
        String categoryName = category.getName();
        String categoryDescription = category.getDescription();
        String categoryDepartment = category.getDepartment();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO productcategories (id, name, description, department) values('"+categoryId+"','"+categoryName+"','"+categoryDescription+"','"+categoryDepartment+"')");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(int id) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM productcategories");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ProductCategory newProductCategory = new ProductCategory(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("department"));
                data.add(newProductCategory);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

}
