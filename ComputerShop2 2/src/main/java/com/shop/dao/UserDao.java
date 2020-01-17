package com.shop.dao;

import com.shop.entities.User;

public interface UserDao {

    public User findByUsername(String username);

    public void save(User user);
    
}
