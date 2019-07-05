package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.model.Cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private List<Cart> data;
    private Map<Integer, Integer> shopcart;
    private List<Integer> products;
    private List<Integer> integers;
    private static CartDaoMem instance = null;
    ProductDao pd = ProductDaoMem.getInstance();

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Cart cart) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO carts (id, user_id, active, historydate, product01, product02, product03, product04, product05, product06, product07, product08, product09, product10, product11, product12, product13, product14, product15, product16, product17, product18, product19, product20, product21, product22, product23, product24, product25, product26, product27, product28, product29, product30) values('"+cart.getId()+"','"+cart.getUser_id()+"','"+cart.getActive()+"','"+cart.getHistorydate()+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Cart> findActive(int user_id) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carts WHERE user_id = " + user_id + " AND active = 'active';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                shopcart = new HashMap<>();
                products = new ArrayList<>();
                integers = new ArrayList<>();
                products.add(rs.getInt("product01"));
                products.add(rs.getInt("product02"));
                products.add(rs.getInt("product03"));
                products.add(rs.getInt("product04"));
                products.add(rs.getInt("product05"));
                products.add(rs.getInt("product06"));
                products.add(rs.getInt("product07"));
                products.add(rs.getInt("product08"));
                products.add(rs.getInt("product09"));
                products.add(rs.getInt("product10"));
                products.add(rs.getInt("product11"));
                products.add(rs.getInt("product12"));
                products.add(rs.getInt("product13"));
                products.add(rs.getInt("product14"));
                products.add(rs.getInt("product15"));
                integers.add(rs.getInt("product16"));
                integers.add(rs.getInt("product17"));
                integers.add(rs.getInt("product18"));
                integers.add(rs.getInt("product19"));
                integers.add(rs.getInt("product20"));
                integers.add(rs.getInt("product21"));
                integers.add(rs.getInt("product22"));
                integers.add(rs.getInt("product23"));
                integers.add(rs.getInt("product24"));
                integers.add(rs.getInt("product25"));
                integers.add(rs.getInt("product26"));
                integers.add(rs.getInt("product27"));
                integers.add(rs.getInt("product28"));
                integers.add(rs.getInt("product29"));
                integers.add(rs.getInt("product30"));
                for(int i = 0; i < products.size(); i++){
                    if(products.get(i) == 0){
                        continue;
                    } else {
                        shopcart.put(products.get(i), integers.get(i));
                    }
                }
                Cart newCart = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getString("active"), rs.getString("historydate"), shopcart);
                data.add(newCart);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public int countItems(int user_id) {
        int items = 0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carts WHERE user_id = " + user_id + " AND active = 'active';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                items += (rs.getInt("product16"));
                items += (rs.getInt("product17"));
                items += (rs.getInt("product18"));
                items += (rs.getInt("product19"));
                items += (rs.getInt("product20"));
                items += (rs.getInt("product21"));
                items += (rs.getInt("product22"));
                items += (rs.getInt("product23"));
                items += (rs.getInt("product24"));
                items += (rs.getInt("product25"));
                items += (rs.getInt("product26"));
                items += (rs.getInt("product27"));
                items += (rs.getInt("product28"));
                items += (rs.getInt("product29"));
                items += (rs.getInt("product30"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return items;
    }

    @Override
    public List<Cart> findAll(int user_id) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carts");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                shopcart = new HashMap<>();
                products = new ArrayList<>();
                integers = new ArrayList<>();
                products.add(rs.getInt("product01"));
                products.add(rs.getInt("product02"));
                products.add(rs.getInt("product03"));
                products.add(rs.getInt("product04"));
                products.add(rs.getInt("product05"));
                products.add(rs.getInt("product06"));
                products.add(rs.getInt("product07"));
                products.add(rs.getInt("product08"));
                products.add(rs.getInt("product09"));
                products.add(rs.getInt("product10"));
                products.add(rs.getInt("product11"));
                products.add(rs.getInt("product12"));
                products.add(rs.getInt("product13"));
                products.add(rs.getInt("product14"));
                products.add(rs.getInt("product15"));
                integers.add(rs.getInt("product16"));
                integers.add(rs.getInt("product17"));
                integers.add(rs.getInt("product18"));
                integers.add(rs.getInt("product19"));
                integers.add(rs.getInt("product20"));
                integers.add(rs.getInt("product21"));
                integers.add(rs.getInt("product22"));
                integers.add(rs.getInt("product23"));
                integers.add(rs.getInt("product24"));
                integers.add(rs.getInt("product25"));
                integers.add(rs.getInt("product26"));
                integers.add(rs.getInt("product27"));
                integers.add(rs.getInt("product28"));
                integers.add(rs.getInt("product29"));
                integers.add(rs.getInt("product30"));
                for(int i = 0; i < products.size(); i++){
                    shopcart.put(products.get(i), integers.get(i));
                }
                Cart newCart = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getString("active"), rs.getString("historydate"), shopcart);
                data.add(newCart);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

}
