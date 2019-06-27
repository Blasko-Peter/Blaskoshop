package com.blasko.shop.model;

import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.implementation.ProductCategoryDaoMem;

public class ProductCategory {

    private int id;
    private String name;
    private String description;
    private String department;

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();

    public ProductCategory(String name, String description, String department) {
        this.id = ((pcd.getAll()).size()) + 1;
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public ProductCategory(int id, String name, String description, String department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }

}
