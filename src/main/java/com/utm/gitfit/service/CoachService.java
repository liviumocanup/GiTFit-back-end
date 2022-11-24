package com.utm.gitfit.service;

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

    private Coach getById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Coach with id: " + id + ", not found."));
    }
}
