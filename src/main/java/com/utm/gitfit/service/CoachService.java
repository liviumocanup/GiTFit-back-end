package com.utm.gitfit.service;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.dto.UserDtoRequest;
import com.utm.gitfit.exception.EntityInvalidInputException;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.repository.ClientRepository;
import com.utm.gitfit.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.utm.gitfit.mapper.CoachMapper.mapToDto;
import static com.utm.gitfit.mapper.CoachMapper.mapToEntity;
import static com.utm.gitfit.mapper.response.CoachResponseMapper.mapToResponse;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<CoachResponse> findAll() {
        List<Coach> coachList = coachRepository.findAll();

        return coachList.stream()
                .map(mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CoachResponse findById(Long id) {
        return coachToResponse(findCoachById(id));
    }

    @Transactional
    public CoachResponse save(CoachDto coachDto) {
        Coach coach = coachToEntity(coachDto);

        coach.setClients(new ArrayList<>());

        return coachToResponse(coachRepository.save(coach));
    }

    @Transactional
    public CoachResponse update(long id, CoachDto coachDto) {
        Coach repoCoach = findCoachById(id);

        repoCoach.setName(coachDto.getName())
                .setLastName(coachDto.getLastName())
                .setEmail(coachDto.getEmail())
                .setUsername(coachDto.getUsername())
                .setPassword(coachDto.getPassword())
                .setBirthday(coachDto.getBirthday())
                .setBankAccountId(coachDto.getBankAccountId());

        return saveToResponse(repoCoach);
    }

    @Transactional
    public CoachResponse addClients(long id, List<UserDtoRequest> clientRequests) {
        Coach repoCoach = findCoachById(id);
        List<Client> repoClientsList = repoCoach.getClients();

        if (repoClientsList == null) {
            repoCoach.setClients(new ArrayList<>());
        }

        for (UserDtoRequest clientRequest : clientRequests) {
            Client client = findClientByIdAndFullName(clientRequest);
            if (repoClientsList.contains(client))
                throw new EntityInvalidInputException("Coach with id: " + repoCoach.getId() + ", already has client " + client.getId());
            else {
                repoClientsList.add(client);
                client.setCoach(repoCoach);
                clientRepository.save(client);
            }
        }

        repoCoach.setClients(repoClientsList);
        return saveToResponse(repoCoach);
    }

    @Transactional
    public CoachResponse removeClients(long id, List<UserDtoRequest> clientRequests) {
        Coach repoCoach = findCoachById(id);
        List<Client> repoClientsList = repoCoach.getClients();

        if (repoClientsList == null || repoClientsList.isEmpty()) {
            throw new EntityNotFoundException("Coach with id: " + repoCoach.getId() + ", does not have any clients to remove.");
        } else {
            for (UserDtoRequest clientRequest : clientRequests) {
                Client client = findClientByIdAndFullName(clientRequest);
                if (!repoClientsList.contains(client))
                    throw new EntityInvalidInputException("Coach with id: " + repoCoach.getId() + ", does not have client " + client.getId());
                else {
                    repoClientsList.remove(client);
                    client.setCoach(null);
                    clientRepository.save(client);
                }
            }

            repoCoach.setClients(repoClientsList);
            return saveToResponse(repoCoach);
        }
    }

    private CoachResponse saveToResponse(Coach coach) {
        return coachToResponse(coachRepository.save(coach));
    }

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + ", not found."));
    }

    private Client findClientByIdAndFullName(UserDtoRequest clientRequest) {
        return clientRepository.findByIdAndNameAndLastName(clientRequest.getId(), clientRequest.getName(), clientRequest.getLastName())
                .orElseThrow(() -> new EntityNotFoundException("Client: " + clientRequest + ", not found."));
    }

    private CoachDto coachToDto(Coach coach) {
        return mapToDto.apply(coach);
    }

    private Coach coachToEntity(CoachDto coachDto) {
        return mapToEntity.apply(coachDto);
    }

    private CoachResponse coachToResponse(Coach coach) {
        return mapToResponse.apply(coach);
    }
}
