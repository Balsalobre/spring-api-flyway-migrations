package com.cbo.layered.controller;

import com.cbo.layered.model.dto.ClientDto;
import com.cbo.layered.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping()
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto update(@PathVariable UUID clientId, @RequestBody ClientDto client) {
        return clientService.updateByClientId(clientId, client);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId) {
        clientService.deleteByClientId(clientId);
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getById(@PathVariable UUID clientId) {
        return clientService.findByClientId(clientId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ClientDto> getAll() {
        return clientService.findAll();
    }
}
