package com.tms.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestDto {
    @NotNull
    @Size(min = 2, max = 20)
    private String firstname;

    public void setPassword(@NotNull @NotBlank String password) {
        this.password = password;
    }

    public void setLogin(@NotNull @NotBlank String login) {
        this.login = login;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public void setSecondName(@NotNull @Size(min = 2, max = 20) String secondName) {
        this.secondName = secondName;
    }

    public void setFirstname(@NotNull @Size(min = 2, max = 20) String firstname) {
        this.firstname = firstname;
    }

    public @NotNull @NotBlank String getPassword() {
        return password;
    }

    public @NotNull @NotBlank String getLogin() {
        return login;
    }

    public @NotNull @Size(min = 2, max = 20) String getFirstname() {
        return firstname;
    }

    public @NotNull @Size(min = 2, max = 20) String getSecondName() {
        return secondName;
    }

    public @Email String getEmail() {
        return email;
    }

    @NotNull
    @Size(min = 2, max = 20)
    private String secondName;

    @Setter
    @Getter
    private Integer age;

    @Email
    private String email;
    @Setter
    @Getter
    private String sex;

    @Setter
    @Getter
    private String telephoneNumber;

    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String password;
}
