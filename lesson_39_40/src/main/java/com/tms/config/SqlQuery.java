package com.tms.config;

public interface SqlQuery {

    //User
    String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    String DELETE_USER = "DELETE from users WHERE id = ?";
    String UPDATE_USER = "UPDATE users SET firstname=?,secondname=?,age=?,mobile_phone=?,email=?,sex=?,updated=DEFAULT WHERE id=?";
    String CREATE_USER = "INSERT INTO users (id, firstname, secondname, age, mobile_phone, email, created, updated, sex, is_deleted) " +
            "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, ?, ?, ?)";
    String GET_USERS = "SELECT * FROM users";

    //Security
    String GET_SECURITY_BY_ID = "SELECT * FROM security WHERE id = ?";
    String CREATE_SECURITY = "INSERT INTO security (id, login, password, role, created, updated, user_id) " +
            "VALUES (DEFAULT, ?, ?, ?, DEFAULT, ?, ?)";
    String UPDATE_SECURITY = "UPDATE security SET login=?,password=?,updated=DEFAULT WHERE id=?";
    String DELETE_SECURITY = "UPDATE security SET login=NULL,password=NULL,updated=DEFAULT WHERE id=?";

    //Product
    String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    String CREATE_PRODUCT = "INSERT INTO public.product (id, name, price, created, updated) VALUES (DEFAULT, ?, ?, ?, ?)";
    String UPDATE_PRODUCT = "UPDATE product SET name=?,price=?,updated=DEFAULT WHERE id=?";
    String DELETE_PRODUCT = "DELETE from product WHERE id=?";
    String GET_PRODUCTS = "SELECT * FROM product";
}
