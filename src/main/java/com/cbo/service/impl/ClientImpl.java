package com.cbo.service.impl;

import com.cbo.model.dao.ClientRepository;
import com.cbo.model.entity.Client;
import com.cbo.service.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class ClientImpl implements IClient {
    @Autowired
    private ClientRepository clientDAO;

    @Transactional
    @Override
    public Client save(Client client) {
        return clientDAO.save(client);
    }

    @Transactional
    @Override
    public Client updateByClientId(UUID clientId, Client client) {
        return clientDAO.findById(clientId)
                .map(existingClient -> {
                    existingClient.setName(client.getName());
                    existingClient.setSurname(client.getSurname());
                    existingClient.setEmail(client.getEmail());
                    return existingClient;
                })
                .orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findByClientId(UUID clientId) {
        return clientDAO.findById(clientId).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Client> findAll() {
        return clientDAO.findAll();
    }

    @Transactional
    @Override
    public void deleteByClientId(UUID clientId) {
        clientDAO.deleteById(clientId);
    }
}
