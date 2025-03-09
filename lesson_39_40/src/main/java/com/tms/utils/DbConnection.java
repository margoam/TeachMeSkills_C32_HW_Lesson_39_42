package com.tms.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
@Component
public class DbConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/db-lesson-mvc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
