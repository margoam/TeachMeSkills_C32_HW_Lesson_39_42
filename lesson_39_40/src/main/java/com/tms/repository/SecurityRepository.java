package com.tms.repository;

import com.tms.config.SqlQuery;
import com.tms.model.Role;
import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.utils.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.Optional;

@Repository
public class SecurityRepository {

    private final DbConnection databaseService;

    @Autowired
    public SecurityRepository(DbConnection databaseService) {
        this.databaseService = databaseService;
    }

    public void registration(RegistrationDto requestDto) throws SQLException {
        Connection connection = databaseService.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement createUserStatement = connection.prepareStatement(SqlQuery.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            createUserStatement.setString(1, requestDto.getFirstname());
            createUserStatement.setString(2, requestDto.getSecondName());
            createUserStatement.setInt(3, requestDto.getAge());
            createUserStatement.setString(4, requestDto.getTelephoneNumber());
            createUserStatement.setString(5, requestDto.getEmail());
            createUserStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            createUserStatement.setString(7, requestDto.getSex());
            createUserStatement.setBoolean(8, false);
            createUserStatement.executeUpdate();

            ResultSet generatedKeys = createUserStatement.getGeneratedKeys();
            long userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }

            PreparedStatement createSecurityStatement = connection.prepareStatement(SqlQuery.CREATE_SECURITY);
            createSecurityStatement.setString(1, requestDto.getLogin());
            createSecurityStatement.setString(2, requestDto.getPassword());
            createSecurityStatement.setString(3, Role.USER.toString());
            createSecurityStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            createSecurityStatement.setLong(5, userId);
            createSecurityStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new SQLException("Registration failed: " + e.getMessage(), e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Boolean updateSecurity(Security security) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_SECURITY);
            statement.setString(1, security.getLogin());
            statement.setString(2, security.getPassword());
            statement.setLong(3, security.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating security: " + e.getMessage());
            return false;
        }
    }

    public Optional<Security> getSecurityById(Long id) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_SECURITY_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return parseSecurity(resultSet);
        } catch (SQLException e) {
            System.out.println("Error finding security by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean deleteSecurity(Long id) {
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_SECURITY);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting security: " + e.getMessage());
            return false;
        }
    }

    private Optional<Security> parseSecurity(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Security security = new Security();
            security.setId(resultSet.getLong("id"));
            security.setLogin(resultSet.getString("login"));
            security.setPassword(resultSet.getString("password"));
            security.setRole(Role.valueOf(resultSet.getString("role")));
            security.setCreated(resultSet.getTimestamp("created"));
            security.setUpdated(resultSet.getTimestamp("updated"));
            security.setUserId(resultSet.getLong("user_id"));
            return Optional.of(security);
        }
        return Optional.empty();
    }
}
