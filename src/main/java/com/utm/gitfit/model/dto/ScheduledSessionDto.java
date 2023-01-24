package com.utm.gitfit.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScheduledSessionDto {
    private CoachDto coach;
    private ClientDto client;
    private LocalDateTime dateAndTime;

    private Integer duration;
}
