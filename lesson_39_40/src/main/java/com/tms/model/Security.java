package com.tms.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Security {
    @Setter
    @Getter
    private Long id;
    @NotBlank(message = "Login can't be empty")
    private String login;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Role can't be empty")
    private Role role;
    @Setter
    @Getter
    private Timestamp created;

    public @NotBlank(message = "Login can't be empty") String getLogin() {
        return login;
    }

    public void setLogin(@NotBlank(message = "Login can't be empty") String login) {
        this.login = login;
    }

    public @NotBlank(message = "Password can't be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password can't be empty") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Role can't be empty") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role can't be empty") Role role) {
        this.role = role;
    }

    @Setter
    @Getter
    private Timestamp updated;
    @Setter
    @Getter
    private Long userId;
}
