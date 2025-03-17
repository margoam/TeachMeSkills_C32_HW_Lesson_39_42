package com.tms.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class User {

    private Long id;
    @NotBlank(message = "Name can't be empty")
    private String firstname;
    @NotBlank(message = "Lastname can't be empty")
    private String secondName;
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
    private String email;
    private String sex;
    private String telephoneNumber;
    private Timestamp created;
    private Timestamp updated;
    private Boolean isDeleted;
    private Security securityInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Security getSecurityInfo() {
        return securityInfo;
    }

    public void setSecurityInfo(Security securityInfo) {
        this.securityInfo = securityInfo;
    }
}
