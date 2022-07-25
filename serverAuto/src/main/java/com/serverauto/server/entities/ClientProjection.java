package com.serverauto.server.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection для получения полной информации о Клиенте
 */
@Projection(name = "clientInfo", types = {Client.class})
public interface ClientProjection {

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

}