package com.serverauto.server.entities;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection для получения полной информации о Продаже
 */
@Projection(name = "fullInfo", types = {Sale.class})
public interface SaleProjection {
    Long getId();

    String getStatus();

    String getDateOfSale();

    String getMethodOfPayment();

    ClientProjection getClient();

    EmployeeProjection getEmployee();

    CarProjection getCar();

    Integer getPrice();


}
