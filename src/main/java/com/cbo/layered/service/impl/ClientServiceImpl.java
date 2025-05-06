package com.cbo.layered.service.impl;

import com.cbo.layered.infrastructure.mybatis.ClientMapper;
import com.cbo.layered.model.dto.ClientDto;
import com.cbo.layered.model.entity.Client;
import com.cbo.layered.service.IClientServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientServiceImplementation {

    private final ClientMapper clientMapper;

    @Transactional
    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = convertToEntity(clientDto);
        client.setClientId(UUID.randomUUID());
        client.setRegistrationDate(new Date());
        client.setUpdatedAt(new Date());

        clientMapper.save(client);
        return convertToDto(client);
    }

    @Transactional
    @Override
    public ClientDto updateByClientId(UUID clientId, ClientDto clientDto) {
        Client existingClient = clientMapper.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found" + clientId));

        existingClient.setName(clientDto.getName());
        existingClient.setSurname(clientDto.getSurname());
        existingClient.setPhone(clientDto.getPhone());
        existingClient.setEmail(clientDto.getEmail());
        existingClient.setUpdatedAt(new Date());

        clientMapper.update(clientId, existingClient);
        return convertToDto(existingClient);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto findByClientId(UUID clientId) {
        return clientMapper.findById(clientId).map(this::convertToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientDto> findAll() {
        return StreamSupport.stream(clientMapper.findAll().spliterator(), false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteByClientId(UUID clientId) {
        clientMapper.deleteById(clientId);
    }

    /** ********************************************************* */

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