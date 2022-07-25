package com.serverauto.server.services;

import com.serverauto.server.entities.Car;
import com.serverauto.server.entities.Client;
import com.serverauto.server.entities.Employee;
import com.serverauto.server.entities.Sale;
import com.serverauto.server.repositories.CarRepository;
import com.serverauto.server.repositories.ClientRepository;
import com.serverauto.server.repositories.EmployeeRepository;
import com.serverauto.server.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepo;

    @Autowired
    CarRepository carRepo;

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    public Boolean addSale(Map<String, ?> data) {
        Boolean checkCorrect = true;
        for (String i : data.keySet()) {
            System.out.println(data.get(i));
            if (data.get(i) == null) {
                checkCorrect = false;
            }
        }
        if (checkCorrect) {
            String dateOfSale = (String) data.get("dateOfSale");
            String methodOfPayment = (String) data.get("methodOfPayment");
            String status = (String) data.get("status");
            Integer price = Integer.valueOf((String) data.get("price"));

            Client client = clientRepo.getOne(Long.valueOf((Integer) data.get("client_id")));
            Car car = carRepo.getOne(Long.valueOf((Integer) data.get("car_id")));
            Employee employee = employeeRepo.getOne(Long.valueOf((Integer) data.get("employee_id")));

            Sale sale = new Sale(dateOfSale, methodOfPayment, status, price, client, car, employee);
            saleRepo.save(sale);
        }
        return checkCorrect;
    }
}
