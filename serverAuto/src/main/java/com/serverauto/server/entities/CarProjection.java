package com.serverauto.server.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection для получения полной информации о Автомобиле
 */
@Projection(name = "carInfo", types = {Car.class})
public interface CarProjection {
    Long getId();

    String getBrand();

    String getModel();

    String getColour();

    String getDateOfManufacture();

    Boolean getAvailable();

    Integer getPrice();

    String getLocationOfAssembly();

    String getNumberOfBody();

    String getNumberOfEngine();

    String getVin();
}
