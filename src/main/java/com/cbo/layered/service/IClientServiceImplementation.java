package com.cbo.layered.service;

import com.cbo.layered.model.dto.ClientDto;
import java.util.UUID;

public interface IClientServiceImplementation {
    ClientDto save(ClientDto client);

    ClientDto updateByClientId(UUID clientId, ClientDto client);

    ClientDto findByClientId(UUID clientId);

    Iterable<ClientDto> findAll();

    void deleteByClientId(UUID clientId);
}
