package com.blasko.shop.dao;

import com.blasko.shop.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    List<User> find(String email);
    boolean checkEmail(String email);
    boolean checkPassword (String password);
    List<User> getAll();
    String hashedPassword(String password);
    boolean checkPassword(String password, String hash);

}
