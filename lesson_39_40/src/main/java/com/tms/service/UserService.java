package com.tms.service;

import com.tms.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    Optional<User> createUser(User user) throws SQLException;

    Optional<User> updateUser(User user);

    Optional<User> deleteUser(Long id);
}
