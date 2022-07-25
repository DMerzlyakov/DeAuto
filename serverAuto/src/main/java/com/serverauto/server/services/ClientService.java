package com.serverauto.server.services;

import com.serverauto.server.entities.Car;
import com.serverauto.server.entities.Client;
import com.serverauto.server.entities.Employee;
import com.serverauto.server.repositories.CarRepository;
import com.serverauto.server.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientService {

    // Проверка для нобавления
    // Проверка для изменения
    @Autowired
    ClientRepository clientRepo;

    public Boolean addClient(Map<String,?> data){
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
            String numberPassport = (String) data.get("numberPassport");
            String serialPassport = (String) data.get("serialPassport");
            String email = (String) data.get("email");
            String birthday = (String) data.get("birthday");
            String dateOfRegistration = (String) data.get("dateOfRegistration");
            System.out.println("vin");
            Client client = new Client(firstName, lastName,middleName, phoneNumber, numberPassport,
                                       serialPassport, email, birthday, dateOfRegistration);
            clientRepo.save(client);
        }
        return checkCorrect;

    }

}
