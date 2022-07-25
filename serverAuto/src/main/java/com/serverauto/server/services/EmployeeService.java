package com.serverauto.server.services;

import com.serverauto.server.entities.Client;
import com.serverauto.server.entities.Employee;
import com.serverauto.server.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Map;

@Service
public class EmployeeService {
    // Проверка для нобавления
    // Проверка для изменения

    @Autowired
    EmployeeRepository employeeRepo;

    public String checkAuth(String login, String password){
        System.out.println(login + " " + password);
        Employee emp = employeeRepo.findByEmailAndPassword(login, password);
        System.out.println(emp.getUserName() + " " + emp.getPassword());
        if (emp != null && emp.getUserName().equals(login) && emp.getPassword().equals(password)){
                return "valid";
        } else{
            return "Неверный логин/пароль";
        }
    }

    public Boolean addEmployee(Map<String,?> data){
        Boolean checkCorrect = true;
        for (String i: data.keySet()){
            System.out.println(data.get(i));
            if (data.get(i) == null){
                checkCorrect = false;
            }
        }
        if (checkCorrect){
            String firstName = (String) data.get("firstName");
            String lastName = (String) data.get("lastName");
            String middleName = (String) data.get("middleName");
            String phoneNumber = (String) data.get("phoneNumber");
            String vacancy = (String) data.get("vacancy");
            String numberPassport = (String) data.get("numberPassport");
            String serialPassport = (String) data.get("serialPassport");
            String email = (String) data.get("email");
            String password = (String) data.get("password");
            String birthday = (String) data.get("birthday");
            String dateOfRegistration = (String) data.get("dateOfRegistration");
            System.out.println("vin");
            Employee employee = new Employee(firstName, lastName,middleName, phoneNumber, vacancy, numberPassport,
                    serialPassport, email, password, birthday, dateOfRegistration);
            employeeRepo.save(employee);
        }
        return checkCorrect;

    }
}
