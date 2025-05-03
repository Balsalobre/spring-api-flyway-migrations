package com.cbo.controller;

import com.cbo.model.entity.Client;
import com.cbo.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping()
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client update(@PathVariable UUID clientId, @RequestBody Client client) {
        return clientService.updateByClientId(clientId, client);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId) {
        clientService.deleteByClientId(clientId);
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client getById(@PathVariable UUID clientId) {
        return clientService.findByClientId(clientId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Client> getAll() {
        return clientService.findAll();
    }
}
