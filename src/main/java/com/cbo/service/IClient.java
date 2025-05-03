package com.cbo.service;

import com.cbo.model.entity.Client;

import java.util.UUID;

public interface IClient {
    Client save(Client client);

    Client updateByClientId(UUID clientId, Client client);

    Client findByClientId(UUID clientId);

    void deleteByClientId(UUID clientId);
}
