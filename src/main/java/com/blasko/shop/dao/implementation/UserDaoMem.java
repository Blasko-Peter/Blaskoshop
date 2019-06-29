package com.blasko.shop.dao.implementation;

import com.blasko.shop.dao.UserDao;
import com.blasko.shop.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class UserDaoMem implements UserDao {

    private List<User> data;
    private static UserDaoMem instance = null;

    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        int userId = user.getId();
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users (id, email, password) values('"+userId+"','"+userEmail+"','"+userPassword+"')");
            stmt.execute();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<User> find(String email) {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE email = '" + email + "';");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User newuser = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"));
                data.add(newuser);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public boolean checkEmail(String email) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailpat.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkPassword(String password) {
        boolean checking = false;
        if(password.length() > 3){
            checking = true;
        }
        return checking;
    }

    @Override
    public List<User> getAll() {
        data = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "Madrid1975");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User newuser = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"));
                data.add(newuser);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }

    @Override
    public String hashedPassword(String password) {
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password, salt);
        return hashed_password;
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        boolean password_verified = false;
        if(null == hash || !hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        password_verified = BCrypt.checkpw(password, hash);
        return password_verified;
    }
}
