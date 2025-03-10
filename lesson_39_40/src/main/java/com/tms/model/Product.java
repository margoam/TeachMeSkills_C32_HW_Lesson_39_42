package com.tms.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Product {
    @Setter
    @Getter
    private Long id;
    @NotNull
    private String name;

    public @NotNull Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull Double price) {
        this.price = price;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    private Double price;
    @Setter
    @Getter
    private Timestamp created;
    @Setter
    @Getter
    private Timestamp updated;
}
