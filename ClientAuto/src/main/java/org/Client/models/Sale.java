package org.Client.models;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Сущаность Продажа для отображения в JavaFX
 */
public class Sale {

    private final LongProperty id = new SimpleLongProperty();
    private Car car;
    private Client client;
    private Employee employee;
    private final ObjectProperty<LocalDate> dateOfSale = new SimpleObjectProperty<>();
    private final StringProperty methodOfPayment = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final IntegerProperty price = new SimpleIntegerProperty();
    private final StringProperty clientInfo = new SimpleStringProperty();
    private final StringProperty carInfo = new SimpleStringProperty();
    private final StringProperty employeeInfo = new SimpleStringProperty();


    public long getId() {
        return id.get();
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        carInfo.set(car.getBrand() + " " + car.getModel());
    }

    public Client getClient() {
        return client;
    }

    public String getClientInfo() {
        return clientInfo.get();
    }

    public StringProperty clientInfoProperty() {
        return clientInfo;
    }


    public String getCarInfo() {
        return carInfo.get();
    }


    public String getEmployeeInfo() {
        return employeeInfo.get();
    }

    public void setClient(Client client) {
        this.client = client;
        clientInfo.set(client.getFirstName() + " " + client.getLastName());
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        employeeInfo.set(employee.getFirstName() + " " + employee.getLastName());
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LocalDate getDateOfSale() {
        return dateOfSale.get();
    }

    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale.set(dateOfSale);
    }

    public String getMethodOfPayment() {
        return methodOfPayment.get();
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment.set(methodOfPayment);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

}
