package com.utm.gitfit.mapper;

import com.utm.gitfit.dto.TraineeInfoDto;
import com.utm.gitfit.model.TraineeInfo;

import java.util.function.Function;

//@NoArgsConstructor(access = PRIVATE)
public class TraineeInfoMapper {
    public static final Function<TraineeInfo, TraineeInfoDto> mapToDto = traineeInfo ->
            TraineeInfoDto.builder()
                    .id(traineeInfo.getId())
                    .weight(traineeInfo.getWeight())
                    .height(traineeInfo.getHeight())
                    .fatRatio(traineeInfo.getFatRatio())
                    .suggestions(traineeInfo.getSuggestions())
                    .diet(traineeInfo.getDiet())
                    .lastUpdated(traineeInfo.getLastUpdated())
                    .build();

    public static final Function<TraineeInfoDto, TraineeInfo> mapToEntity = traineeInfoDto ->
            TraineeInfo.builder()
                    .id(traineeInfoDto.getId())
                    .weight(traineeInfoDto.getWeight())
                    .height(traineeInfoDto.getHeight())
                    .fatRatio(traineeInfoDto.getFatRatio())
                    .suggestions(traineeInfoDto.getSuggestions())
                    .diet(traineeInfoDto.getDiet())
                    .lastUpdated(traineeInfoDto.getLastUpdated())
                    .build();
}
