package com.blasko.shop.dao.implementation;

import com.blasko.shop.model.Supplier;
import com.blasko.shop.dao.SupplierDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao{

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        int supplierId = supplier.getId();
        String supplierName = supplier.getName();
        String supplierDescription = supplier.getDescription();
        System.out.println("Ez itt a supplier: " + supplierId + "/" + supplierName + "/" + supplierDescription);
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
//            PreparedStatement stmt = con.prepareStatement("INSERT INTO suppliers values('"+supplierId+"','"+supplierName+"','"+supplierDescription+"')");
//            stmt.execute();
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
    }

    @Override
    public void update(int id) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM suppliers");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Supplier newSupplier = new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
                data.add(newSupplier);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }
}
