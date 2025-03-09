package com.tms.service;

import com.tms.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
