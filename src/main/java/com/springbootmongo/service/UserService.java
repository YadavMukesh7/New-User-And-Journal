package com.springbootmongo.service;

import com.springbootmongo.entity.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);

    public User updateUser(User user, String userName);

    public boolean deleteUser(String userName);

    public List<User> findAll();

    public User findByUserName(String userName);

    public User adminUser(User user);
}
