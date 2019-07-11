package com.blasko.shop.model;

import com.blasko.shop.dao.AddressDao;
import com.blasko.shop.dao.implementation.AddressDaoMem;

public class Address {

    private int id;
    private String firstname;
    private String lastname;
    private String address;
    private String postalcode;
    private String city;
    private String country;
    private String phonenumber;
    private User user;

    AddressDao ad = AddressDaoMem.getInstance();

    public Address(String firstname, String lastname, String address, String postalcode, String city, String country, String phonenumber, User user) {
        this.id = ad.findAll().size() + 1;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
        this.phonenumber = phonenumber;
        this.user = user;
    }

    public Address(int id, String firstname, String lastname, String address, String postalcode, String city, String country, String phonenumber, User user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
        this.phonenumber = phonenumber;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
