package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.CartDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.model.Cart;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CartDaoMemTest {

    CartDao cd = CartDaoMem.getInstance();
    ProductDao pd = ProductDaoMem.getInstance();

    @Test
    public void findActive1() {
        int actualListSize = cd.findActive(1).size();
        Assert.assertEquals(1, actualListSize);
    }

    @Test
    public void findActive2() {
        int actualListSize = cd.findActive(0).size();
        Assert.assertEquals(0, actualListSize);
    }

    @Test
    public void countItems() {
        int actualItems = cd.countItems(1);
        Assert.assertEquals(0, actualItems);
    }

    @Test
    public void findAll1() {
        int actualListSize = cd.findAll(1).size();
        Assert.assertEquals(0, actualListSize);
    }

    //I have 5 carts in the database which from there are 2 ordered carts
    @Test
    public void findAll2() {
        int actualListSize = cd.findAll(2).size();
        Assert.assertEquals(2, actualListSize);
    }

    //I have 5 carts in the database
    @Test
    public void getAll() {
        int actualListSize = cd.getAll().size();
        Assert.assertEquals(5, actualListSize);
    }

    @Test
    public void mapConverter() {
        int productId = 1;
        int productCount = 10;
        String productNameControl = pd.find(1).getName();
        Map<Integer, Integer> tester = new HashMap<>();
        tester.put(productId, productCount);
        Map<Product, Integer> converted = cd.mapConverter(tester);
        String productName = "";
        for(Product key : converted.keySet()){
            productName = key.getName();
        }
        Assert.assertEquals(productNameControl, productName);
    }

    @Test
    public void getTotalPrice() {
        Product product = pd.find(1);
        int productPrice = (int) product.getPrice();
        Map<Product, Integer> productAndCount = new HashMap<>();
        productAndCount.put(product, 10);
        int totalPrice = cd.getTotalPrice(productAndCount);
        Assert.assertEquals(productPrice * 10, totalPrice);
    }

    @Test
    public void historyProducts() {
        List<Cart> cart = cd.findActive(2);
        List<Map<Product, Integer>> carts = cd.historyProducts(cart);
        Assert.assertNotNull(carts);
    }
}