package com.serverauto.server.entities;

import lombok.Data;

import javax.persistence.*;


/**
 * Сущность для хранения Автомобилей
 */
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String colour;
    private String dateOfManufacture;
    private Boolean available;
    private Integer price;
    private String locationOfAssembly;
    private String numberOfBody;
    private String numberOfEngine;
    private String vin;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Sale sale;

    public Car(String brand, String model, String colour,
               String dateOfManufacture,
               Integer price, String locationOfAssembly,
               String numberOfBody, String numberOfEngine, String vin, Boolean available) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.dateOfManufacture = dateOfManufacture;
        this.available = available;
        this.price = price;
        this.locationOfAssembly = locationOfAssembly;
        this.vin = vin;
        this.numberOfBody = numberOfBody;
        this.numberOfEngine = numberOfEngine;
    }

    public Car() {

    }
}
