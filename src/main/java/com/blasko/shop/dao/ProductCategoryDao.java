package com.blasko.shop.dao;

import com.blasko.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    void update(int id);
    ProductCategory find(int id);
    List<ProductCategory> getAll();

}
