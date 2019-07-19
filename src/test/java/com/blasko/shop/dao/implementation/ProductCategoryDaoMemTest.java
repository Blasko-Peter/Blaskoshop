package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoMemTest {

    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();

    @Test
    public void findId1() {
        ProductCategory pc = pcd.find(0);
        Assert.assertNull(pc);
    }

    @Test
    public void findId2() {
        ProductCategory pc = pcd.find(1);
        Assert.assertNotNull(pc);
    }

    @Test
    public void find1() {
        ProductCategory pc = pcd.find("asdf");
        Assert.assertNull(pc);
    }

    @Test
    public void find2() {
        ProductCategory pc = pcd.find("Tablet");
        Assert.assertNotNull(pc);
    }

    @Test
    public void getAll() {
        List<ProductCategory> pc = pcd.getAll();
        Assert.assertNotNull(pc);
    }
}