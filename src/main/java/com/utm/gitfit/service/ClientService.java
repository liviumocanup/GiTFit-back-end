package com.utm.gitfit.service;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.ClientMapper;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return clientMapper.mapEntityListToResponseList(clientRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        return clientMapper.mapEntityToResponse(getById(id));
    }

    @Transactional
    public ClientResponse save(ClientDto clientDto) {
        Client clientEntity = clientMapper.mapDtoToEntity(clientDto);

        return clientMapper.mapEntityToResponse(clientRepository.save(clientEntity));
    }

    @Transactional
    public ClientResponse update(ClientDto clientDto) {
        Client repoClient = getById(clientDto.getId());

        repoClient.setName(clientDto.getName())
                .setLastName(clientDto.getLastName())
                .setEmail(clientDto.getEmail())
                .setUsername(clientDto.getUsername())
                .setPassword(clientDto.getPassword())
                .setBirthday(clientDto.getBirthday());

        return clientMapper.mapEntityToResponse(clientRepository.save(repoClient));
    }

    private Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
    }
}
