package com.serverauto.server.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность для хранения Клиентов
 */
@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String dateOfRegistration;
    private String birthday;
    private String serialPassport;
    private String numberPassport;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sale> saleList;

    /**
     * Конструктор для создания клиента
     */
    public Client(String firstName, String lastName, String middleName, String phoneNumber,
                  String numberPassport, String serialPassport, String email, String birthday,
                  String dateOfRegistration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.numberPassport = numberPassport;
        this.serialPassport = serialPassport;
        this.email = email;
        this.birthday = birthday;
        this.dateOfRegistration = dateOfRegistration;

    }

    public Client() {
    }
}

