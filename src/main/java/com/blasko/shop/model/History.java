package com.blasko.shop.model;

import java.util.Map;

public class History {

    private int id;
    private int user_id;
    private String active;
    private String historydate;
    private Map<Product, Integer> history;
    private int address_id;
    private int totalPrice;

    public History(int id, int user_id, String active, String historydate, Map<Product, Integer> history, int address_id, int totalPrice) {
        this.id = id;
        this.user_id = user_id;
        this.active = active;
        this.historydate = historydate;
        this.history = history;
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

    public Map<Product, Integer> getHistory() {
        return history;
    }

    public void setHistory(Map<Product, Integer> history) {
        this.history = history;
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
