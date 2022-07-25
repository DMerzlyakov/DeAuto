package com.serverauto.server.Controllers;

import com.serverauto.server.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CarController {
    // Post Put
    private final CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/car/add")
    public ResponseEntity<?> createCar(@RequestBody Map<String, ?> data){
        System.out.println(data);
        return new ResponseEntity<>(carService.addCar(data), HttpStatus.OK);
    }
}
