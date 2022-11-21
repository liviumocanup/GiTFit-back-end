package com.utm.gitfit.service;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.CoachMapper;
import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.response.CoachResponse;
import com.utm.gitfit.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;

    @Transactional(readOnly = true)
    public List<CoachResponse> findAll() {
        return coachMapper.mapEntityListToResponseList(coachRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CoachResponse findById(Long id) {
        return coachMapper.mapEntityToResponse(getById(id));
    }

    @Transactional
    public CoachResponse save(CoachDto coachDto) {
        Coach coach = coachMapper.mapDtoToEntity(coachDto);
        return coachMapper.mapEntityToResponse(coachRepository.save(coach));
    }

    @Transactional
    public CoachResponse update(CoachDto coachDto) {
        Coach repoCoach = getById(coachDto.getId());

        repoCoach.setName(coachDto.getName())
                .setLastName(coachDto.getLastName())
                .setEmail(coachDto.getEmail())
                .setUsername(coachDto.getUsername())
                .setPassword(coachDto.getPassword())
                .setBirthday(coachDto.getBirthday());
        return coachMapper.mapEntityToResponse(coachRepository.save(repoCoach));
    }

    private Coach getById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + ", not found."));
    }
}
