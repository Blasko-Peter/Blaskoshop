package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;
import com.blasko.shop.service.DatabaseContact;

import java.sql.*;
import java.text.SimpleDateFormat;
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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
    private List<Map<Product, Integer>> histories;
    private DatabaseContact dbc = new DatabaseContact();

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
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO carts (id, user_id, active, historydate, product01, product02, product03, product04, product05, product06, product07, product08, product09, product10, product11, product12, product13, product14, product15, quantity01, quantity02, quantity03, quantity04, quantity05, quantity06, quantity07, quantity08, quantity09, quantity10, quantity11, quantity12, quantity13, quantity14, quantity15, address_id, totalprice) values('"+cart.getId()+"','"+cart.getUser_id()+"','"+cart.getActive()+"','"+cart.getHistorydate()+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"+cart.getAddress_id()+","+cart.getTotalPrice()+");");
            stmt.execute();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Cart> findActive(int user_id) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
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
                integers.add(rs.getInt("quantity01"));
                integers.add(rs.getInt("quantity02"));
                integers.add(rs.getInt("quantity03"));
                integers.add(rs.getInt("quantity04"));
                integers.add(rs.getInt("quantity05"));
                integers.add(rs.getInt("quantity06"));
                integers.add(rs.getInt("quantity07"));
                integers.add(rs.getInt("quantity08"));
                integers.add(rs.getInt("quantity09"));
                integers.add(rs.getInt("quantity10"));
                integers.add(rs.getInt("quantity11"));
                integers.add(rs.getInt("quantity12"));
                integers.add(rs.getInt("quantity13"));
                integers.add(rs.getInt("quantity14"));
                integers.add(rs.getInt("quantity15"));
                for(int i = 0; i < products.size(); i++){
                    if(products.get(i) == 0){
                        continue;
                    } else {
                        shopcart.put(products.get(i), integers.get(i));
                    }
                }
                Cart newCart = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getString("active"), rs.getString("historydate"), shopcart, rs.getInt("address_id"), rs.getInt("totalprice"));
                data.add(newCart);
            }
            con.close();
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
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carts WHERE user_id = " + user_id + " AND active = 'active';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                items += (rs.getInt("quantity01"));
                items += (rs.getInt("quantity02"));
                items += (rs.getInt("quantity03"));
                items += (rs.getInt("quantity04"));
                items += (rs.getInt("quantity05"));
                items += (rs.getInt("quantity06"));
                items += (rs.getInt("quantity07"));
                items += (rs.getInt("quantity08"));
                items += (rs.getInt("quantity09"));
                items += (rs.getInt("quantity10"));
                items += (rs.getInt("quantity11"));
                items += (rs.getInt("quantity12"));
                items += (rs.getInt("quantity13"));
                items += (rs.getInt("quantity14"));
                items += (rs.getInt("quantity15"));
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return items;
    }

    @Override
    public List<Cart> findAll(int user_id) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carts WHERE active = 'ordered' AND user_id = "+user_id+";");
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
                integers.add(rs.getInt("quantity01"));
                integers.add(rs.getInt("quantity02"));
                integers.add(rs.getInt("quantity03"));
                integers.add(rs.getInt("quantity04"));
                integers.add(rs.getInt("quantity05"));
                integers.add(rs.getInt("quantity06"));
                integers.add(rs.getInt("quantity07"));
                integers.add(rs.getInt("quantity08"));
                integers.add(rs.getInt("quantity09"));
                integers.add(rs.getInt("quantity10"));
                integers.add(rs.getInt("quantity11"));
                integers.add(rs.getInt("quantity12"));
                integers.add(rs.getInt("quantity13"));
                integers.add(rs.getInt("quantity14"));
                integers.add(rs.getInt("quantity15"));
                for(int i = 0; i < products.size(); i++){
                    if(products.get(i) == 0){
                        continue;
                    } else {
                        shopcart.put(products.get(i), integers.get(i));
                    }
                }
                Cart newCart = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getString("active"), rs.getString("historydate"), shopcart, rs.getInt("address_id"), rs.getInt("totalprice"));
                data.add(newCart);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public List<Cart> getAll() {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
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
                integers.add(rs.getInt("quantity01"));
                integers.add(rs.getInt("quantity02"));
                integers.add(rs.getInt("quantity03"));
                integers.add(rs.getInt("quantity04"));
                integers.add(rs.getInt("quantity05"));
                integers.add(rs.getInt("quantity06"));
                integers.add(rs.getInt("quantity07"));
                integers.add(rs.getInt("quantity08"));
                integers.add(rs.getInt("quantity09"));
                integers.add(rs.getInt("quantity10"));
                integers.add(rs.getInt("quantity11"));
                integers.add(rs.getInt("quantity12"));
                integers.add(rs.getInt("quantity13"));
                integers.add(rs.getInt("quantity14"));
                integers.add(rs.getInt("quantity15"));
                for(int i = 0; i < products.size(); i++){
                    shopcart.put(products.get(i), integers.get(i));
                }
                Cart newCart = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getString("active"), rs.getString("historydate"), shopcart, rs.getInt("address_id"), rs.getInt("totalprice"));
                data.add(newCart);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public void updateCart(Cart cart) {
        int[] keys = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] values = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int index = 0;
        for(Integer key : cart.getShopcart().keySet()){
            keys[index] = key;
            values[index] = cart.getShopcart().get(key);
            index++;
        }
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("UPDATE carts SET product01 = " + keys[0] + ", product02 = " + keys[1] + ", product03 = " + keys[2] + ", product04 = " + keys[3] + ", product05 = " + keys[4] + ", product06 = " + keys[5] + ", product07 = " + keys[6] + ", product08 = " + keys[7] + ", product09 = " + keys[8] + ", product10 = " + keys[9] + ", product11 = " + keys[10] + ", product12 = " + keys[11] + ", product13 = " + keys[12] + ", product14 = " + keys[13] + ", product15 = " + keys[14] + ", quantity01 = " + values[0] + ", quantity02 = " + values[1] + ", quantity03 = " + values[2] + ", quantity04 = " + values[3] + ", quantity05 = " + values[4] + ", quantity06 = " + values[5] + ", quantity07 = " + values[6] + ", quantity08 = " + values[7] + ", quantity09 = " + values[8] + ", quantity10 = " + values[9] + ", quantity11 = " + values[10] + ", quantity12 = " + values[11] + ", quantity13 = " + values[12] + ", quantity14 = " + values[13] + ", quantity15 = " + values[14] + ", totalprice = " + cart.getTotalPrice() + "  WHERE id = " + cart.getId() + ";");
            stmt.execute();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Map<Product, Integer> mapConverter(Map<Integer, Integer> shopcart) {
        Map<Product, Integer> products = new HashMap<>();
        for(Integer key : shopcart.keySet()){
            Product product = pd.find(key);
            int value = shopcart.get(key);
            products.put(product, value);
        }
        return products;
    }

    @Override
    public int getTotalPrice(Map<Product, Integer> products) {
        int totalPrice = 0;
        for(Product product : products.keySet()){
            int productPrice = (int) product.getPrice();
            int quantity = products.get(product);
            totalPrice += (productPrice * quantity);
        }
        return totalPrice;
    }

    @Override
    public void closeCartById(int cart_id, int address_id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String historydate = sdf.format(timestamp);
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("UPDATE carts SET active = 'ordered', historydate = '"+historydate+"', address_id = "+address_id+" WHERE id = " + cart_id + ";");
            stmt.execute();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Map<Product, Integer>> historyProducts(List<Cart> carts){
        histories = new ArrayList<>();
        for(Cart cart : carts){
            histories.add(mapConverter(cart.getShopcart()));
        }
        return histories;
    }

}
