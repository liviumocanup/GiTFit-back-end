package com.utm.gitfit.service;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.mapper.CoachMapper;
import com.utm.gitfit.model.Coach;
import com.utm.gitfit.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.utm.gitfit.mapper.CoachMapper.mapToDto;
import static com.utm.gitfit.mapper.CoachMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;

    @Transactional(readOnly = true)
    public List<CoachDto> findAll() {
        List<Coach> coachList = coachRepository.findAll();

        return coachList.stream().map(mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CoachDto findById(Long id) {
        return mapToDto.apply(findCoachById(id));
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

        return CoachMapper.mapToDto.apply(repoCoach);
    }

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + " not found."));
    }
}
