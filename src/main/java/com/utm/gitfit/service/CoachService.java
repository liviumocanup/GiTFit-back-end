package com.utm.gitfit.service;

import com.utm.gitfit.dto.ClientDto;
import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.mapper.ClientMapper;
import com.utm.gitfit.mapper.TraineeInfoMapper;
import com.utm.gitfit.model.Client;
import com.utm.gitfit.model.Coach;
import com.utm.gitfit.repository.ClientRepository;
import com.utm.gitfit.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.utm.gitfit.mapper.ClientMapper.mapToDto;
import static com.utm.gitfit.mapper.ClientMapper.mapToEntity;

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
        return mapToDto.apply(findCoachById(id));
    }

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + " not found."));
    }

    @Transactional
    public CoachDto save(CoachDto coachDto) {
        return mapToDto.apply(coachRepository.save(mapToEntity.apply(coachDto)));
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
        repoCoach.setClients(coachDto.getClients());

        return CoachMapper.mapToDto.apply(repoCoach);
    }

    @Transactional(readOnly = true)
    public Client findByName(Long id) {
        return clientRepository.findByName();
    }
}
