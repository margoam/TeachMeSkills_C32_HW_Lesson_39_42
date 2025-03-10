package com.tms.service;

import com.tms.model.Role;
import com.tms.model.Security;
import com.tms.utils.DbConnection;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Security getSecurityByUserId(Long userId) {
        String sql = "SELECT * FROM public.security WHERE user_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRowToSecurity(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching security for userId: " + userId, e);
        }
        return null;
    }

    @Override
    public void createSecurity(Security security) {
        String sql = "INSERT INTO public.security (login, password, role, created, updated, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, security.getLogin());
            preparedStatement.setString(2, security.getPassword());
            preparedStatement.setString(3, security.getRole().toString());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(6, security.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating security for userId: " + security.getUserId(), e);
        }
    }

    @Override
    public void updateSecurity(Security security) {
        String sql = "UPDATE public.security SET login = ?, password = ?, role = ?, updated = ? WHERE user_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, security.getLogin());
            preparedStatement.setString(2, security.getPassword());
            preparedStatement.setString(3, security.getRole().toString());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setLong(5, security.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating security for userId: " + security.getUserId(), e);
        }
    }

    private Security mapRowToSecurity(ResultSet resultSet) throws SQLException {
        Security security = new Security();
        security.setId(resultSet.getLong("id"));
        security.setLogin(resultSet.getString("login"));
        security.setPassword(resultSet.getString("password"));
        security.setRole(Role.valueOf(resultSet.getString("role")));
        security.setCreated(resultSet.getTimestamp("created"));
        security.setUpdated(resultSet.getTimestamp("updated"));
        security.setUserId(resultSet.getLong("user_id"));
        return security;
    }
}
