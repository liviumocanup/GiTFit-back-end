package com.utm.gitfit.mapper;

import com.utm.gitfit.model.dto.ScheduledSessionDto;
import com.utm.gitfit.model.entities.ScheduledSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {CoachMapper.class})
public interface ScheduledSessionMapper {
    ScheduledSessionDto mapEntityToDto(ScheduledSession scheduledSession);

    ScheduledSession mapDtoToEntity(ScheduledSessionDto scheduledSessionDto);
}
