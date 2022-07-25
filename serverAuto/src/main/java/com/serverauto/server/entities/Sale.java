package com.serverauto.server.entities;

import lombok.Data;

import javax.persistence.*;


/**
 * Сущность для хранения Продаж
 */
@Entity
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    private Car car;

    private String dateOfSale;
    private String methodOfPayment;
    private String status;
    private Integer price;

    /**
     * Конструктор для создания Продажи
     */
    public Sale(String dateOfSale, String methodOfPayment, String status,
                Integer price, Client client, Car car, Employee employee) {
        this.client = client;
        this.car = car;
        this.employee = employee;

        this.dateOfSale = dateOfSale;
        this.status = status;
        this.price = price;
        this.methodOfPayment = methodOfPayment;

    }

    public Sale() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }
}
