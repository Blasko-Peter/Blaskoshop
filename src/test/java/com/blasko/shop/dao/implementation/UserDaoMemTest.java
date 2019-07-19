package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoMemTest {

    UserDao ud = UserDaoMem.getInstance();

    @Test
    public void find01() {
        String controlEmail = "admin@admin.hu";
        int size = ud.find(controlEmail).size();
        Assert.assertNotEquals(0, size);
    }

    @Test
    public void find02() {
        String controlEmail = "asdf@asdf.com";
        int size = ud.find(controlEmail).size();
        Assert.assertEquals(0, size);
    }

    @Test
    public void find1() {
        int userId = 1;
        int size = ud.find(userId).size();
        Assert.assertEquals(1, size);
    }

    @Test
    public void find2() {
        int userId = 0;
        int size = ud.find(userId).size();
        Assert.assertEquals(0, size);
    }

    @Test
    public void checkEmail1() {
        String testerEmail = "asdf.as.df@df@.asd.asd";
        Assert.assertFalse(ud.checkEmail(testerEmail));
    }

    @Test
    public void checkEmail2() {
        String testerEmail = "admin@admin.hu";
        Assert.assertTrue(ud.checkEmail(testerEmail));
    }

    @Test
    public void checkPassword1() {
        String controlPassword = "asd";
        Assert.assertFalse(ud.checkPassword(controlPassword));
    }

    @Test
    public void checkPassword2() {
        String controlPassword = "asdf";
        Assert.assertTrue(ud.checkPassword(controlPassword));
    }

    @Test
    public void checkPassword3() {
        String controlPassword = "admin";
        String hash = ud.find(1).get(0).getPassword();
        Assert.assertTrue(ud.checkPassword(controlPassword, hash));
    }

    @Test
    public void getAll() {
        Assert.assertNotEquals(0, ud.getAll().size());
    }
}