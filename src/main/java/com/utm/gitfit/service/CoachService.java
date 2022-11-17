package com.utm.gitfit.service;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.dto.UserDtoRequest;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.Client;
import com.utm.gitfit.model.Coach;
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

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<CoachDto> findAll() {
        List<Coach> coachList = coachRepository.findAll();

        return coachList.stream().map(mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CoachDto findById(Long id) {
        return coachToDto(findCoachById(id));
    }

    @Transactional
    public CoachDto save(CoachDto coachDto) {
        return coachToDto(coachRepository.save(coachToEntity(coachDto)));
    }

    @Transactional
    public CoachDto update(long id, CoachDto coachDto) {
        Coach repoCoach = findCoachById(id);

        repoCoach.setName(coachDto.getName());
        repoCoach.setLastName(coachDto.getLastName());
        repoCoach.setEmail(coachDto.getEmail());
        repoCoach.setUsername(coachDto.getUsername());
        repoCoach.setPassword(coachDto.getPassword());
        repoCoach.setBirthday(coachDto.getBirthday());
        repoCoach.setBankAccountId(coachDto.getBankAccountId());

        return saveEntity(repoCoach);
    }

    @Transactional
    public CoachDto addClients(long id, List<UserDtoRequest> clientRequests) {
        Coach repoCoach = findCoachById(id);

        if (repoCoach.getClients() == null) {
            repoCoach.setClients(new ArrayList<>());
        }

        for (UserDtoRequest clientRequest : clientRequests) {
            repoCoach.getClients().add(findClientByIdAndFullName(clientRequest));
        }

        return saveEntity(repoCoach);
    }

    @Transactional
    public CoachDto removeClients(long id, List<UserDtoRequest> clientRequests) {
        Coach repoCoach = findCoachById(id);

        if (repoCoach.getClients() == null || repoCoach.getClients().isEmpty()) {
            throw new EntityNotFoundException("Coach with id: " + repoCoach.getId() + ", does not have any clients to remove.");
        }

        for (UserDtoRequest clientRequest : clientRequests) {
            repoCoach.getClients().remove(findClientByIdAndFullName(clientRequest));
        }

        return saveEntity(repoCoach);
    }

    private CoachDto saveEntity(Coach coach) {
        return save(coachToDto(coach));
    }

    private Client findClientByIdAndFullName(UserDtoRequest clientRequest) {
        return clientRepository.findByIdAndNameAndLastName(clientRequest.getId(), clientRequest.getName(), clientRequest.getLastName())
                .orElseThrow(() -> new EntityNotFoundException("Client: " + clientRequest + ", not found."));
    }

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + ", not found."));
    }

    private CoachDto coachToDto(Coach coach) {
        return mapToDto.apply(coach);
    }

    private Coach coachToEntity(CoachDto coachDto) {
        return mapToEntity.apply(coachDto);
    }
}
