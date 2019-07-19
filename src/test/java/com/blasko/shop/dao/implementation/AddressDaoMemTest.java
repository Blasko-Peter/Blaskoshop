package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.AddressDao;
import com.blasko.shop.model.Address;
import org.junit.Assert;

import java.util.List;

public class AddressDaoMemTest {

    AddressDao ad = AddressDaoMem.getInstance();

    @org.junit.Test
    public void getAllAddressToUser1() {
        int actualListSize = ad.getAllAddressToUser(1).size();
        Assert.assertEquals(1, actualListSize);
    }

    @org.junit.Test
    public void getAllAddressToUser100() {
        int actualListSize = ad.getAllAddressToUser(100).size();
        Assert.assertEquals(0, actualListSize);
    }


    //Now there are altogether 2 addresses
    @org.junit.Test
    public void findAll() {
        int actualListSize = ad.findAll().size();
        Assert.assertEquals(2, actualListSize);
    }

    @org.junit.Test
    public void checkAddresses1() {
        List<Address> addresses = ad.findAll();
        String firstName = "admin";
        String lastName = "admin";
        String address = "admin";
        String postalcode = "admin";
        String city = "admin";
        String country = "admin";
        String phoneNumber = "admin";
        int actualId = ad.checkAddresses(addresses, firstName, lastName, address, postalcode, city, country, phoneNumber);
        Assert.assertEquals(1, actualId);
    }

    @org.junit.Test
    public void checkAddresses2() {
        List<Address> addresses = ad.findAll();
        String firstName = "asdf";
        String lastName = "asdf";
        String address = "asdf";
        String postalcode = "asdf";
        String city = "asdf";
        String country = "asdf";
        String phoneNumber = "asdf";
        int actualId = ad.checkAddresses(addresses, firstName, lastName, address, postalcode, city, country, phoneNumber);
        Assert.assertEquals(0, actualId);
    }


    @org.junit.Test
    public void getAddressToAddress1() {
        int actualListSize = ad.getAddressToAddress("admin").size();
        Assert.assertEquals(1, actualListSize);
    }

    @org.junit.Test
    public void getAddressToAddress2() {
        int actualListSize = ad.getAddressToAddress("asdf").size();
        Assert.assertEquals(0, actualListSize);
    }
}