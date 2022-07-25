package com.serverauto.server.Controllers;

import com.serverauto.server.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClientController {

    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client/add")
    public ResponseEntity<?> createClient(@RequestBody Map<String, ?> data){
        System.out.println(data);
        return new ResponseEntity<>(clientService.addClient(data), HttpStatus.OK);
    }

}
