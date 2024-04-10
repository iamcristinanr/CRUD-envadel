package com.envadel.Envadel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a car entity.
 */

@Document
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private String manufacture_year;
    private String color;


    /**
     * Default constructor.
     */

    public Car() {
    }

    /**
     * Constructs a car with specified brand, model, color, and manufacture year.
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param color The color of the car.
     * @param manufacture_year The manufacture year of the car.
     */

    public Car(String brand, String model, String color, String manufacture_year) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufacture_year = manufacture_year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacture_year() {
        return manufacture_year;
    }

    public void setManufacture_year(String manufacture_year) {
        this.manufacture_year = manufacture_year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
