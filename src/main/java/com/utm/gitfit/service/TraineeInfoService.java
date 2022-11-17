package com.utm.gitfit.service;

import com.utm.gitfit.dto.TraineeInfoDto;
import com.utm.gitfit.exception.EntityNotFoundException;
import com.utm.gitfit.model.Client;
import com.utm.gitfit.model.TraineeInfo;
import com.utm.gitfit.repository.TraineeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.utm.gitfit.mapper.TraineeInfoMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class TraineeInfoService {
    private final TraineeInfoRepository traineeInfoRepository;

    @Transactional
    public TraineeInfo save(TraineeInfoDto traineeInfoDto, Client client) {
        TraineeInfo traineeInfo = mapToEntity.apply(traineeInfoDto);
        traineeInfo.setClient(client);
        return traineeInfoRepository.save(traineeInfo);
    }

    @Transactional
    public TraineeInfo update(TraineeInfoDto traineeInfoDto) {
        TraineeInfo newTraineeInfo = mapToEntity.apply(traineeInfoDto);
        TraineeInfo repoTraineeInfo = findTraineeInfoById(newTraineeInfo.getId());

        repoTraineeInfo.setId(newTraineeInfo.getId());
        repoTraineeInfo.setWeight(newTraineeInfo.getWeight());
        repoTraineeInfo.setHeight(newTraineeInfo.getHeight());
        repoTraineeInfo.setFatRatio(newTraineeInfo.getFatRatio());
        repoTraineeInfo.setSuggestions(newTraineeInfo.getSuggestions());
        repoTraineeInfo.setDiet(newTraineeInfo.getDiet());
        repoTraineeInfo.setLastUpdated(newTraineeInfo.getLastUpdated());

        return traineeInfoRepository.save(repoTraineeInfo);
    }

    private TraineeInfo findTraineeInfoById(Long id) {
        return traineeInfoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TraineeInfo with id: " + id + " not found."));
    }
}
