package com.anilerc.commandmicroservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT_COMMAND")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    private double price;

    public Product( String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
