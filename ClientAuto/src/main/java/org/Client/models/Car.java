package org.Client.models;

import javafx.beans.property.*;
import org.Client.utils.DateUtil;

import java.time.LocalDate;

/**
 * Сущаность Авто для отображения в JavaFX
 */
public class Car {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty brand = new SimpleStringProperty();
    private final StringProperty model = new SimpleStringProperty();
    private final StringProperty colour = new SimpleStringProperty();
    private final IntegerProperty price = new SimpleIntegerProperty();
    private final BooleanProperty available = new SimpleBooleanProperty();
    private final StringProperty numberEngine = new SimpleStringProperty();
    private final StringProperty numberBody = new SimpleStringProperty();
    private final StringProperty countryOfManufacture = new SimpleStringProperty();
    private final StringProperty vin = new SimpleStringProperty();

    private final ObjectProperty<LocalDate> dateOfManufacture = new SimpleObjectProperty<>();

    public String getVin() {
        return vin.get();
    }

    public void setVin(String vin) {
        this.vin.set(vin);
    }


    public Car() {
    }

    public long getId() {
        return id.get();
    }

    public void setId(String link) {
        String[] buff = link.split("/");
        this.id.set(Long.parseLong(buff[buff.length - 1]));
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getColour() {
        return colour.get();
    }

    public void setColour(String colour) {
        this.colour.set(colour);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public boolean isAvailable() {
        return available.get();
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    public String getNumberEngine() {
        return numberEngine.get();
    }

    public void setNumberEngine(String numberEngine) {
        this.numberEngine.set(numberEngine);
    }

    public String getNumberBody() {
        return numberBody.get();
    }

    public void setNumberBody(String numberBody) {
        this.numberBody.set(numberBody);
    }


    public String getCountryOfManufacture() {
        return countryOfManufacture.get();
    }

    public void setCountryOfManufacture(String countryOfManufacture) {
        this.countryOfManufacture.set(countryOfManufacture);
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture.get();
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture.set(DateUtil.parse(dateOfManufacture));
    }
}
