package com.tms.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class User {
    @Setter
    @Getter
    private Long id;
    @NotBlank(message = "Name can't be empty")
    private String firstname;
    @NotBlank(message = "Lastname can't be empty")
    private String secondName;
    @Setter
    @Getter
    private Integer age;

    public @NotBlank(message = "Name can't be empty") String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotBlank(message = "Name can't be empty") String firstname) {
        this.firstname = firstname;
    }

    public @NotBlank(message = "Lastname can't be empty") String getSecondName() {
        return secondName;
    }

    public void setSecondName(@NotBlank(message = "Lastname can't be empty") String secondName) {
        this.secondName = secondName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String sex;
    @Setter
    @Getter
    private String telephoneNumber;
    @Setter
    @Getter
    private Timestamp created;
    @Setter
    @Getter
    private Timestamp updated;
    private Boolean isDeleted;
    @Getter
    @Setter
    private Security securityInfo;
}
