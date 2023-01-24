package com.utm.gitfit.mapper;

import com.utm.gitfit.model.dto.CoachDto;
import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.response.CoachResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BillingDetailsMapper.class})
public interface CoachMapper {

    CoachDto mapEntityToDto(Coach coach);

    Coach mapDtoToEntity(CoachDto coachDto);

    CoachResponse mapEntityToResponse(Coach coach);

    List<CoachResponse> mapEntityListToResponseList(List<Coach> coaches);
}
