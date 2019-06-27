package com.blasko.shop.model;


import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.dao.implementation.SupplierDaoMem;

public class Supplier {

    private int id;
    private String name;
    private String description;

    SupplierDao sd = SupplierDaoMem.getInstance();

    public Supplier(String name, String description) {
        this.id = ((sd.getAll()).size()) + 1;
        this.name = name;
        this.description = description;
    }

    public Supplier(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

}
