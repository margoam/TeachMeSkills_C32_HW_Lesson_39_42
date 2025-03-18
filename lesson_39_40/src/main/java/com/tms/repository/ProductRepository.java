package com.tms.repository;

import com.tms.config.SqlQuery;
import com.tms.model.Product;
import com.tms.utils.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final DbConnection dbConnection;

    @Autowired
    public ProductRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Optional<Product> findById(Long id) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SqlQuery.GET_PRODUCT_BY_ID
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return parseProduct(resultSet);
        } catch (SQLException e) {
            System.out.println("Error finding product by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        Connection connection = dbConnection.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SqlQuery.GET_PRODUCTS
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCreated(resultSet.getTimestamp("created"));
                product.setUpdated(resultSet.getTimestamp("updated"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all products: " + e.getMessage());
        }
        return products;
    }

    public Optional<Product> save(Product product) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SqlQuery.CREATE_PRODUCT,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(4, null);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.out.println("Error saving product: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Boolean update(Product product) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteById(Long id) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SqlQuery.DELETE_PRODUCT
            );
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    private Optional<Product> parseProduct(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCreated(resultSet.getTimestamp("created"));
            product.setUpdated(resultSet.getTimestamp("updated"));
            return Optional.of(product);
        }
        return Optional.empty();
    }
}
