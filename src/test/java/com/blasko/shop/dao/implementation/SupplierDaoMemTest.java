package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.model.Supplier;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SupplierDaoMemTest {

    SupplierDao sd = SupplierDaoMem.getInstance();

    @Test
    public void findId1() {
        Supplier supplier = sd.find(0);
        Assert.assertNull(supplier);
    }

    @Test
    public void findId2() {
        Supplier supplier = sd.find(1);
        Assert.assertNotNull(supplier);
    }

    @Test
    public void find1() {
        Supplier supplier = sd.find("asdf");
        Assert.assertNull(supplier);
    }

    @Test
    public void find2() {
        Supplier supplier = sd.find("Apple");
        Assert.assertNotNull(supplier);
    }

    @Test
    public void getAll() {
        List<Supplier> suppliers = sd.getAll();
        Assert.assertNotNull(suppliers);
    }
}