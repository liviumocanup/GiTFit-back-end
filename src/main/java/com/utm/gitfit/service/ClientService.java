package com.utm.gitfit.service;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.dto.UserDtoRequest;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.Client;
import com.utm.gitfit.model.Coach;
import com.utm.gitfit.repository.ClientRepository;
import com.utm.gitfit.repository.CoachRepository;
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
    private final CoachRepository coachRepository;
    private final TraineeInfoService traineeInfoService;

    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        List<Client> clientList = clientRepository.findAll();

        return clientList.stream().map(mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        return clientToDto(findClientById(id));
    }

    @Transactional
    public ClientDto save(ClientDto clientDto) {
        Client clientEntity = clientToEntity(clientDto);
        clientEntity.setTraineeInfo(
                traineeInfoService.save(clientDto.getTraineeInfo()
                        , clientEntity)
        );

        return clientToDto(clientRepository.save(clientEntity));
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

        repoClient.setTraineeInfo(
                traineeInfoService.update(clientDto.getTraineeInfo())
        );

        return saveEntity(repoClient);
    }

    @Transactional
    public ClientDto addCoach(long id, UserDtoRequest coachRequest) {
        Client repoClient = findClientById(id);

        repoClient.setCoach(findCoachByIdAndFullName(coachRequest));

        return saveEntity(repoClient);
    }

    @Transactional
    public ClientDto removeCoach(long id) {
        Client repoClient = findClientById(id);

        if (repoClient.getCoach() == null) {
            throw new EntityNotFoundException("Client with id: " + repoClient.getId() + ", does not have a coach to remove.");
        }

        return saveEntity(repoClient);
    }

    private ClientDto saveEntity(Client client) {
        return save(clientToDto(client));
    }

    private Coach findCoachByIdAndFullName(UserDtoRequest coachRequest) {
        return coachRepository.findByIdAndNameAndLastName(coachRequest.getId(), coachRequest.getName(), coachRequest.getLastName())
                .orElseThrow(() -> new EntityNotFoundException("Coach: " + coachRequest + ", not found."));
    }

    private Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found."));
    }

    private ClientDto clientToDto(Client client) {
        return mapToDto.apply(client);
    }

    private Client clientToEntity(ClientDto clientDto) {
        return mapToEntity.apply(clientDto);
    }
}
