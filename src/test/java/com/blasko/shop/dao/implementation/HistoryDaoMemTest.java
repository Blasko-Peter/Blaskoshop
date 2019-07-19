package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.HistoryDao;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.History;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HistoryDaoMemTest {

    CartDao cd = CartDaoMem.getInstance();
    HistoryDao hd = HistoryDaoMem.getInstance();

    @Test
    public void histories1() {
        List<Cart> cart = cd.findAll(0);
        List<History> histories = hd.histories(cart);
        Assert.assertEquals(0, histories.size());
    }

    @Test
    public void histories2() {
        List<Cart> cart = cd.findAll(1);
        List<History> histories = hd.histories(cart);
        Assert.assertNotNull(histories);
    }
}