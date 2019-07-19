package com.blasko.shop.model;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.implementation.CartDaoMem;

import java.util.Map;

public class Cart {

    private int id;
    private int user_id;
    private String active;
    private String historydate;
    private Map<Integer, Integer> shopcart;
    private int address_id;
    private int totalPrice;

    CartDao cd = CartDaoMem.getInstance();

    public Cart(int user_id, String active, String historydate, Map<Integer, Integer> shopcart, int address_id, int totalPrice) {
        this.id = cd.getAll().size() + 1;
        this.user_id = user_id;
        this.active = active;
        this.historydate = historydate;
        this.shopcart = shopcart;
        this.address_id = address_id;
        this.totalPrice = totalPrice;
    }

    public Cart(int id, int user_id, String active, String historydate, Map<Integer, Integer> shopcart, int address_id, int totalPrice) {
        this.id = id;
        this.user_id = user_id;
        this.active = active;
        this.historydate = historydate;
        this.shopcart = shopcart;
        this.address_id = address_id;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getHistorydate() {
        return historydate;
    }

    public void setHistorydate(String historydate) {
        this.historydate = historydate;
    }

    public Map<Integer, Integer> getShopcart() {
        return shopcart;
    }

    public void setShopcart(Map<Integer, Integer> shopcart) {
        this.shopcart = shopcart;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
