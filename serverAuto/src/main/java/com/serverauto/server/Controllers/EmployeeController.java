package com.serverauto.server.Controllers;

import com.serverauto.server.services.ClientService;
import com.serverauto.server.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/auth", params = {"login", "password"})
    public ResponseEntity<?> checkAuth(@RequestParam String login, @RequestParam String password){
        return new ResponseEntity<>(employeeService.checkAuth(login, password), HttpStatus.OK);
    }

    @PostMapping("/employee/add")
    public ResponseEntity<?> createEmployee(@RequestBody Map<String, ?> data){
        System.out.println(data);
        return new ResponseEntity<>(employeeService.addEmployee(data), HttpStatus.OK);
    }
}
