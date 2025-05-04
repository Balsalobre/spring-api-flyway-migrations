package com.cbo.service.impl;

import com.cbo.model.dao.ClientRepository;
import com.cbo.model.dto.ClientDto;
import com.cbo.model.entity.Client;
import com.cbo.service.IClientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements IClientServiceImplementation {
    @Autowired
    private ClientRepository clientDAO;

    @Transactional
    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = convertToEntity(clientDto);
        Client savedClient = clientDAO.save(client);
        return convertToDto(savedClient);
    }

    @Transactional
    @Override
    public ClientDto updateByClientId(UUID clientId, ClientDto clientDto) {
        return clientDAO.findById(clientId).map(existingClient -> {
            existingClient.setName(clientDto.getName());
            existingClient.setSurname(clientDto.getSurname());
            existingClient.setPhone(clientDto.getPhone());
            existingClient.setEmail(clientDto.getEmail());
            existingClient.setUpdatedAt(new Date());
            Client updatedClient = clientDAO.save(existingClient);
            return convertToDto(updatedClient);
        }).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto findByClientId(UUID clientId) {
        return clientDAO.findById(clientId).map(this::convertToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientDto> findAll() {
        return StreamSupport.stream(clientDAO.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteByClientId(UUID clientId) {
        clientDAO.deleteById(clientId);
    }

    private Client convertToEntity(ClientDto clientDto) {
        return Client.builder()
                .clientId(clientDto.getClientId())
                .name(clientDto.getName())
                .surname(clientDto.getSurname())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .updatedAt(clientDto.getUpdatedAt())
                .registrationDate(clientDto.getRegistrationDate())
                .build();
    }

    private ClientDto convertToDto(Client client) {
        return ClientDto.builder()
                .clientId(client.getClientId())
                .name(client.getName())
                .surname(client.getSurname())
                .phone(client.getPhone())
                .email(client.getEmail())
                .updatedAt(client.getUpdatedAt())
                .registrationDate(client.getRegistrationDate())
                .build();
    }
}