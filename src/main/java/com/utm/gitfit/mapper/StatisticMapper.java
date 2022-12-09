package com.utm.gitfit.mapper;

import com.utm.gitfit.model.request.StatisticRequest;
import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.entities.Statistic;
import com.utm.gitfit.model.response.StatisticResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        uses = {SuggestionMapper.class})
public interface StatisticMapper {
    StatisticResponse mapEntityToResponse(Statistic statistic);

    Statistic mapRequestToEntity(StatisticRequest statisticRequest);
}
