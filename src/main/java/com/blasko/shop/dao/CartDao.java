package com.blasko.shop.dao;

import com.blasko.shop.model.Cart;

import java.util.List;

public interface CartDao {

    void add(Cart cart);
    List<Cart> findActive(int user_id);
    int countItems(int user_id);
    List<Cart> findAll(int user_id);
    List<Cart> getAll();


}
