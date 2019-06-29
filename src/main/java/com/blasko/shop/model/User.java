package com.blasko.shop.model;

import com.blasko.shop.dao.UserDao;
import com.blasko.shop.dao.implementation.UserDaoMem;

public class User {

    private int id;
    private String email;
    private String password;

    UserDao ud = UserDaoMem.getInstance();

    public User(String email, String password) {
        this.id = ((ud.getAll()).size()) + 1;
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
