package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.AddressDao;
import com.blasko.shop.dao.UserDao;
import com.blasko.shop.model.Address;
import com.blasko.shop.service.DatabaseContact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoMem implements AddressDao {

    private List<Address> data;
    private static AddressDaoMem instance = null;
    UserDao ud = UserDaoMem.getInstance();
    private DatabaseContact dbc = new DatabaseContact();

    private AddressDaoMem() {
    }

    public static AddressDaoMem getInstance() {
        if (instance == null) {
            instance = new AddressDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Address address) {
        int addressId = address.getId();
        String firstname = address.getFirstname();
        String lastname = address.getLastname();
        String actualaddress = address.getAddress();
        String postalcode = address.getPostalcode();
        String city = address.getCity();
        String country = address.getCountry();
        String phonenumber = address.getPhonenumber();
        int userid = address.getUser().getId();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO addresses (id, firstname, lastname, address, postalcode, city, country, phonenumber, user_id) values('"+addressId+"','"+firstname+"','"+lastname+"','"+actualaddress+"','"+postalcode+"','"+city+"','"+country+"','"+phonenumber+"','"+userid+"')");
            stmt.execute();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Address> getAllAddressToUser(int user_id) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM addresses WHERE user_id = " + user_id + ";");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address newAddress = new Address(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("postalcode"), rs.getString("city"), rs.getString("country"), rs.getString("phonenumber"), ud.find(rs.getInt("user_id")).get(0));
                data.add(newAddress);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public List<Address> findAll() {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM addresses");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address newAddress = new Address(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("postalcode"), rs.getString("city"), rs.getString("country"), rs.getString("phonenumber"), ud.find(rs.getInt("user_id")).get(0));
                data.add(newAddress);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public int checkAddresses(List<Address> addresses, String firstName, String lastName, String address, String postalCode, String city, String country, String phoneNumber) {
        int address_id = 0;
        for(Address add : addresses){
            if(add.getFirstname().equals(firstName) && add.getLastname().equals(lastName) && add.getAddress().equals(address) && add.getPostalcode().equals(postalCode) && add.getCity().equals(city) && add.getCountry().equals(country) && add.getPhonenumber().equals(phoneNumber)){
                address_id = add.getId();
            }
        }
        return address_id;
    }

    @Override
    public List<Address> getAddressToAddress(String address) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(dbc.url, dbc.user, dbc.password);
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM addresses WHERE address = '"+address+"';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Address newAddress = new Address(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), rs.getString("postalcode"), rs.getString("city"), rs.getString("country"), rs.getString("phonenumber"), ud.find(rs.getInt("user_id")).get(0));
                data.add(newAddress);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

}
