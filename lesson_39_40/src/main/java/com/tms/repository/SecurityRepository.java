package com.tms.repository;

import com.tms.model.Role;
import com.tms.model.dto.RegistrationRequestDto;
import com.tms.utils.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class SecurityRepository {

    public DbConnection databaseService;

    @Autowired
    public SecurityRepository(DbConnection databaseService) {
        this.databaseService = databaseService;
    }

    public Boolean registration(RegistrationRequestDto requestDto) throws SQLException {
        Connection connection = DbConnection.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement createUserStatement = connection.prepareStatement("INSERT INTO users (id, firstname, second_name, age, telephone_number, email, created, updated, sex, is_deleted) " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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

            PreparedStatement createSecurityStatement = connection.prepareStatement("INSERT INTO security (id, login, password, role, created, updated, user_id) " +
                    "VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?)");
            createSecurityStatement.setString(1, requestDto.getLogin());
            createSecurityStatement.setString(2, requestDto.getPassword());
            createSecurityStatement.setString(3, Role.USER.toString());
            createSecurityStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            createSecurityStatement.setLong(5, userId);
            createSecurityStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        return false;
    }
}