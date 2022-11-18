package com.utm.gitfit.service;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.dto.UserDtoRequest;
import com.utm.gitfit.exception.EntityInvalidInputException;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.response.ClientResponse;
import com.utm.gitfit.repository.ClientRepository;
import com.utm.gitfit.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.utm.gitfit.mapper.ClientMapper.mapToDto;
import static com.utm.gitfit.mapper.ClientMapper.mapToEntity;
import static com.utm.gitfit.mapper.response.ClientResponseMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CoachRepository coachRepository;
    private final TraineeInfoService traineeInfoService;

    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        List<Client> clientList = clientRepository.findAll();

        return clientList.stream()
                .map(mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        return clientToResponse(findClientById(id));
    }

    @Transactional
    public ClientResponse save(ClientDto clientDto) {
        Client clientEntity = clientToEntity(clientDto);
        clientEntity.setTraineeInfo(
                traineeInfoService.save(clientDto.getTraineeInfo()
                        , clientEntity)
        );

        return clientToResponse(clientRepository.save(clientEntity));
    }

    @Transactional
    public ClientResponse update(long id, ClientDto clientDto) {
        Client repoClient = findClientById(id);

        repoClient.setName(clientDto.getName())
                .setLastName(clientDto.getLastName())
                .setEmail(clientDto.getEmail())
                .setUsername(clientDto.getUsername())
                .setPassword(clientDto.getPassword())
                .setBirthday(clientDto.getBirthday())
                .setBankAccountId(clientDto.getBankAccountId())
                .setTraineeInfo(
                        traineeInfoService.update(clientDto.getTraineeInfo())
                );

        return saveToResponse(repoClient);
    }

    @Transactional
    public ClientResponse addCoach(long id, UserDtoRequest coachRequest) {
        Client repoClient = findClientById(id);
        Coach coach = findCoachByIdAndFullName(coachRequest);
        List<Client> coachClients = coach.getClients();

        if (coachClients.contains(repoClient))
            throw new EntityInvalidInputException("Coach with id: " + coach.getId() + ", already has client " + repoClient.getId());
        else {
            coachClients.add(repoClient);
            coach.setClients(coachClients);
        }

        repoClient.setCoach(coach);
        repoClient.setCoach(coachRepository.save(coach));
        return saveToResponse(repoClient);
    }

    @Transactional
    public ClientResponse removeCoach(long id) {
        Client repoClient = findClientById(id);

        if (repoClient.getCoach() == null) {
            throw new EntityNotFoundException("Client with id: " + repoClient.getId() + ", does not have a coach to remove.");
        }

        repoClient.setCoach(null);
        return saveToResponse(repoClient);
    }

    private ClientResponse saveToResponse(Client client) {
        return clientToResponse(clientRepository.save(client));
    }

    private Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
    }

    private Coach findCoachByIdAndFullName(UserDtoRequest coachRequest) {
        return coachRepository.findByIdAndNameAndLastName(coachRequest.getId(), coachRequest.getName(), coachRequest.getLastName())
                .orElseThrow(() -> new EntityNotFoundException("Coach: " + coachRequest + ", not found."));
    }

    private ClientDto clientToDto(Client client) {
        return mapToDto.apply(client);
    }

    private Client clientToEntity(ClientDto clientDto) {
        return mapToEntity.apply(clientDto);
    }

    private ClientResponse clientToResponse(Client client) {
        return mapToResponse.apply(client);
    }
}
