package com.blasko.shop.model;

import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.implementation.ProductDaoMem;

public class Product {

    ProductDao pd = ProductDaoMem.getInstance();

    private int id;
    private String name;
    private String description;
    private float price;
    private String currency;
    ProductCategory productcategory;
    Supplier supplier;
    private String active;
    private String image;

    public Product(String name, String description, float price, String currency, ProductCategory productcategory, Supplier supplier, String active, String image) {
        this.id = ((pd.getAll()).size()) + 1;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.productcategory = productcategory;
        this.supplier = supplier;
        this.active = active;
        this.image = image;
    }

    public Product(int id, String name, String description, float price, String currency, ProductCategory productcategory, Supplier supplier, String active, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.productcategory = productcategory;
        this.supplier = supplier;
        this.active = active;
        this.image = image;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ProductCategory getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(ProductCategory productcategory) {
        this.productcategory = productcategory;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPriceForm() {
        return String.valueOf(this.price) + " " + this.currency;
    }

}
