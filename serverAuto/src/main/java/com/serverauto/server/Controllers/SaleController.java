package com.serverauto.server.Controllers;

import com.serverauto.server.services.EmployeeService;
import com.serverauto.server.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaleController {

    private final SaleService saleService;


    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale/add")
    public ResponseEntity<?> createEmployee(@RequestBody Map<String, ?> data){
        System.out.println(data);
        return new ResponseEntity<>(saleService.addSale(data), HttpStatus.OK);
    }

}
