package com.invoice_system.demo.controllers;


import com.invoice_system.demo.models.Client;
import com.invoice_system.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId) {
        Client client = clientRepository.getClientById(clientId);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.createClient(client.getName(), client.getEmail(),
                client.getAddress(), client.getCity(),
                client.getPostCode(), client.getCountry());
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
                                               @RequestBody Client clientDetails) {
        Client updatedClient = clientRepository.updateClient(clientId, clientDetails.getName(),
                clientDetails.getEmail(), clientDetails.getAddress(),
                clientDetails.getCity(), clientDetails.getPostCode(),
                clientDetails.getCountry());
        if(updatedClient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
        clientRepository.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }
}
