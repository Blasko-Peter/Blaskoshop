package com.blasko.shop.dao;

import com.blasko.shop.model.Address;

import java.util.List;

public interface AddressDao {

    void add(Address address);
    List<Address> getAllAddressToUser(int user_id);
    List<Address> findAll();
    int checkAddresses(List<Address> addresses, String firstName, String lastName, String address, String postalCode, String city, String country, String phoneNumber);
    Address getAddress(int address_id);

}
