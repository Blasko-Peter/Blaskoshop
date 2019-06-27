package com.blasko.shop.dao;

import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import com.blasko.shop.model.Supplier;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    void update(int id);
    Product find(int id);
    List<Product> getAll();
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);

}
