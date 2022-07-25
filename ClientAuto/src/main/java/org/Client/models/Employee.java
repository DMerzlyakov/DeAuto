package org.Client.models;

import javafx.beans.property.*;
import org.Client.utils.DateUtil;

import java.time.LocalDate;

/**
 * Сущаность Работник для отображения в JavaFX
 */
public class Employee {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty vacancy = new SimpleStringProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty middleName = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty serialPassport = new SimpleStringProperty();
    private final StringProperty numberPassport = new SimpleStringProperty();

    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dateOfRegistration = new SimpleObjectProperty<>();


    public long getId() {
        return id.get();
    }


    public void setId(String link) {
        String[] buff = link.split("/");
        this.id.set(Long.parseLong(buff[buff.length - 1]));
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

    public StringProperty getEmployeeFirstAndSecondName() {
        return new SimpleStringProperty(firstName.get() + " " + lastName.get());
    }

    public String getVacancy() {
        return vacancy.get();
    }


    public void setVacancy(String vacancy) {
        this.vacancy.set(vacancy);
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

    public String getPassword() {
        return password.get();
    }


    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getBirthday() {
        return DateUtil.format(birthday.get());
    }


    public void setBirthday(String birthday) {
        this.birthday.set(DateUtil.parse(birthday));
    }

    public String getDateOfRegistration() {
        return DateUtil.format(dateOfRegistration.get());
    }


    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration.set(DateUtil.parse(dateOfRegistration));
    }

    public String getAge() {
        return String.valueOf(DateUtil.getNowDateAsDate().getYear() - birthday.get().getYear());
    }
}
