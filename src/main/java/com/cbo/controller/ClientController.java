package com.cbo.controller;

import com.cbo.model.entity.Client;
import com.cbo.service.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private IClient clientService;

    @PostMapping()
    public Client create(Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{clientId}")
    public Client update(@PathVariable UUID clientId, Client client) {
        return clientService.updateByClientId(clientId, client);
    }

    @DeleteMapping("/{clientId}")
    public void delete(@PathVariable UUID clientId) {
        clientService.deleteByClientId(clientId);
    }

    @GetMapping("/{clientId}")
    public Client getById(@PathVariable UUID clientId) {
        return clientService.findByClientId(clientId);
    }
}
