package com.blasko.shop.dao;

import com.blasko.shop.model.Supplier;
import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    void update(int id);
    Supplier find(int id);
    Supplier find(String name);
    List<Supplier> getAll();

}
