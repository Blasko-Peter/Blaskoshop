package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.ProductCategoryDao;
import com.blasko.shop.dao.ProductDao;
import com.blasko.shop.dao.SupplierDao;
import com.blasko.shop.model.Product;
import com.blasko.shop.model.ProductCategory;
import com.blasko.shop.model.Supplier;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoMemTest {

    ProductDao pd = ProductDaoMem.getInstance();
    SupplierDao sd = SupplierDaoMem.getInstance();
    ProductCategoryDao pcd = ProductCategoryDaoMem.getInstance();

    @Test
    public void find1() {
        Product product = pd.find(0);
        Assert.assertNull(product);
    }

    @Test
    public void find2() {
        Product product = pd.find(1);
        Assert.assertNotNull(product);
    }

    @Test
    public void getAll1() {
        List<Product> products = pd.getAll();
        Assert.assertNotNull(products);
    }

    //I have 4 products in my database
    @Test
    public void getAll2() {
        List<Product> products = pd.getAll();
        Assert.assertEquals(4, products.size());
    }

    @Test
    public void getBy() {
        Supplier supplier = sd.find(1);
        List<Product> productBySupplier = pd.getBy(supplier);
        Assert.assertNotNull(productBySupplier);
    }

    @Test
    public void getBy1() {
        ProductCategory pc = pcd.find(1);
        List<Product> productByCategory = pd.getBy(pc);
        Assert.assertNotNull(productByCategory);
    }

    @Test
    public void getBy2() {
        Supplier supplier = sd.find(1);
        ProductCategory pc = pcd.find(1);
        List<Product> products = pd.getBy(pc, supplier);
        Assert.assertNotNull(products);
    }
}