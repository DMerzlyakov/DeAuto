package com.serverauto.server.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность для хранения Работников
 */
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String vacancy;


    private String email;
    private String password;
    private String serialPassport;
    private String numberPassport;

    private String birthday;
    private String dateOfRegistration;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Sale> saleList;

    /**
     * Конструктор для создания Работника
     */
    public Employee(String firstName, String lastName, String middleName, String phoneNumber,
                    String vacancy, String numberPassport, String serialPassport, String email,
                    String password, String birthday, String dateOfRegistration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.vacancy = vacancy;
        this.numberPassport = numberPassport;
        this.serialPassport = serialPassport;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.dateOfRegistration = dateOfRegistration;

    }

    public Employee() {

    }


    public String getUserName() {
        return email;
    }

    public void setUserName(String userName) {
        this.email = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
