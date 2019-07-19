package com.blasko.shop.dao;

import com.blasko.shop.model.Cart;
import com.blasko.shop.model.History;

import java.util.List;

public interface HistoryDao {

    List<History> histories(List<Cart> carts);

}
