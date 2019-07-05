package com.blasko.shop.dao;

import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDao {

    void add(Cart cart);
    List<Cart> findActive(int user_id);
    int countItems(int user_id);
    List<Cart> findAll(int user_id);
    List<Cart> getAll();
    void updateCart(Cart cart);
    Map<Product, Integer> mapConverter(Map<Integer, Integer> shopcart);

}
