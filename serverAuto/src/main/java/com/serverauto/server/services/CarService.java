package com.serverauto.server.services;

import com.serverauto.server.entities.Car;
import com.serverauto.server.repositories.CarRepository;
import com.serverauto.server.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CarService {
    // Проверка для нобавления
    // Проверка для изменения

    @Autowired
    CarRepository carRepo;

    public Boolean addCar(Map<String,?> data){
        Boolean checkCorrect = true;
        for (String i: data.keySet()){
            System.out.println(data.get(i));
            if (data.get(i) == null){
                checkCorrect = false;
            }
        }
        if (checkCorrect){
            String brand = (String) data.get("brand");
            String model = (String) data.get("model");
            String colour = (String) data.get("colour");
            String dateOfManufacture = (String) data.get("dateOfManufacture");
            Boolean available = (Boolean) data.get("available");
            Integer price = (Integer) data.get("price");
    //        String description = data.get("description");
            String locationOfAssembly = (String) data.get("locationOfAssembly");
            String numberOfBody = (String) data.get("numberOfBody");
            String numberOfEngine = (String) data.get("numberOfEngine");
            String vin = (String) data.get("VIN");
            System.out.println("vin");
            Car car = new Car(brand, model, colour, dateOfManufacture,
                    price, locationOfAssembly, numberOfBody, numberOfEngine, vin, available);
            carRepo.save(car);
        }
        return checkCorrect;

    }
}
