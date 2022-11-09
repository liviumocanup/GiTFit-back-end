package com.utm.gitfit.service;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.ClientMapper;
import com.utm.gitfit.mapper.TraineeInfoMapper;
import com.utm.gitfit.model.Client;
import com.utm.gitfit.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.utm.gitfit.mapper.ClientMapper.mapToDto;
import static com.utm.gitfit.mapper.ClientMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        List<Client> clientList = clientRepository.findAll();

        return clientList.stream().map(mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        return mapToDto.apply(findClientById(id));
    }

    @Transactional
    public ClientDto save(ClientDto clientDto) {
        return mapToDto.apply(clientRepository.save(mapToEntity.apply(clientDto)));
    }

    @Transactional
    public ClientDto update(long id, ClientDto clientDto) {
        Client repoClient = findClientById(id);

        repoClient.setName(clientDto.getName());
        repoClient.setLastName(clientDto.getLastName());
        repoClient.setEmail(clientDto.getEmail());
        repoClient.setUsername(clientDto.getUsername());
        repoClient.setPassword(clientDto.getPassword());
        repoClient.setBirthday(clientDto.getBirthday());
        repoClient.setBankAccountId(clientDto.getBankAccountId());
        repoClient.setTraineeInfo(TraineeInfoMapper.mapToEntity.apply(clientDto.getTraineeInfo()));

        return ClientMapper.mapToDto.apply(repoClient);
    }

    private Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
    }
}
