package com.tms.repository;

import com.tms.config.SqlQuery;
import com.tms.model.Role;
import com.tms.model.Security;
import com.tms.model.User;
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
            System.out.println(e.getMessage());
            connection.rollback();
        }
    }

    public Boolean updateSecurity(Security security) throws SQLException {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement getSecurityStatement = connection.prepareStatement(SqlQuery.UPDATE_SECURITY);
            getSecurityStatement.setString(1, security.getLogin());
            getSecurityStatement.setString(1, security.getPassword());
            return getSecurityStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Security> getSecurityById(Long id) {
        Connection connection = databaseService.getConnection();

        try {
            PreparedStatement getSecurityStatement = connection.prepareStatement(SqlQuery.GET_SECURITY_BY_ID);
            getSecurityStatement.setLong(1, id);
            ResultSet resultSet = getSecurityStatement.executeQuery();
            return parseSecurity(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Boolean deleteSecurity(Long id){
        Connection connection = databaseService.getConnection();
        try {
            PreparedStatement getSecurityStatement = connection.prepareStatement(SqlQuery.DELETE_SECURITY);
            getSecurityStatement.setLong(1, id);
            return getSecurityStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
