package com.serverauto.server.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection для получения полной информации о Работнике
 */
@Projection(name = "employeeInfo", types = {Employee.class})
public interface EmployeeProjection {
    Long getId();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getPhoneNumber();

    String getEmail();

    String getDateOfRegistration();

    String getBirthday();

    String getSerialPassport();

    String getNumberPassport();

    String getVacancy();

    String getPassword();
}
