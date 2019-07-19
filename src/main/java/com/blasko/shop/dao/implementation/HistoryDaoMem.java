package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.HistoryDao;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryDaoMem implements HistoryDao {

    private List<History> data;
    private static HistoryDaoMem instance = null;
    CartDao cd = CartDaoMem.getInstance();

    private HistoryDaoMem() {
    }

    public static HistoryDaoMem getInstance() {
        if (instance == null) {
            instance = new HistoryDaoMem();
        }
        return instance;
    }

    @Override
    public List<History> histories(List<Cart> carts) {
        data = new ArrayList<>();
        for(Cart cart : carts){
            History newHistory = new History(cart.getId(), cart.getUser_id(), cart.getActive(), cart.getHistorydate(), cd.mapConverter(cart.getShopcart()), cart.getAddress_id(), cart.getTotalPrice());
            data.add(newHistory);
        }
        return data;
    }

}
