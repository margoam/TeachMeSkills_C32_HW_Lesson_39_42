package com.tms.service;

import com.tms.model.User;
import com.tms.utils.DbConnection;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM public.users WHERE id = ? AND is_deleted = false";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRowToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user with id: " + id, e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM public.users WHERE is_deleted = false";
        List<User> users = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(mapRowToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users", e);
        }
        return users;
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO public.users (firstname, second_name, age, telephone_number, email, sex, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setUserPreparedStatement(preparedStatement, user);
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE public.users SET firstname = ?, second_name = ?, age = ?, telephone_number = ?, email = ?, sex = ?, updated = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setUserPreparedStatement(preparedStatement, user);
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(8, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user with id: " + user.getId(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "UPDATE public.users SET is_deleted = true WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user with id: " + id, e);
        }
    }

    private User mapRowToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setSecondName(resultSet.getString("second_name"));
        user.setAge(resultSet.getInt("age"));
        user.setTelephoneNumber(resultSet.getString("telephone_number"));
        user.setEmail(resultSet.getString("email"));
        user.setSex(resultSet.getString("sex"));
        user.setCreated(resultSet.getTimestamp("created"));
        user.setUpdated(resultSet.getTimestamp("updated"));
        user.setDeleted(resultSet.getBoolean("is_deleted"));
        return user;
    }

    private void setUserPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getTelephoneNumber());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getSex());
    }
}
