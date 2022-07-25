package org.Client.models;

import javafx.beans.property.*;
import org.Client.utils.DateUtil;

import java.time.LocalDate;
/**
 * Сущаность Клиент для отображения в JavaFX
 */
public class Client {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty middleName = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();

    private final StringProperty serialPassport = new SimpleStringProperty();
    private final StringProperty numberPassport = new SimpleStringProperty();

    private final StringProperty email = new SimpleStringProperty();


    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dateOfRegistration = new SimpleObjectProperty<>();

    public String getAge(){
        return String.valueOf(DateUtil.getNowDateAsDate().getYear() - birthday.get().getYear());
    }

    public String getSerialPassport() {
        return serialPassport.get();
    }

    public void setSerialPassport(String serialPassport) {
        this.serialPassport.set(serialPassport);
    }

    public String getNumberPassport() {
        return numberPassport.get();
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport.set(numberPassport);
    }

    public long getId() {
        return id.get();
    }

    public StringProperty getFirstAndLastName(){
        return new SimpleStringProperty(firstName.get() + " "+ lastName.get());
    }

    public void setId(String link) {
        String[] buff = link.split("/");
        this.id.set(Long.parseLong(buff[buff.length - 1]));
    }

    public String getFirstName() {
        return firstName.get();
    }


    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(DateUtil.parse(birthday));
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration.get();
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration.set(DateUtil.parse(dateOfRegistration));
    }
}
