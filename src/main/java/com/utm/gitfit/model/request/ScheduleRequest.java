package com.utm.gitfit.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ScheduleRequest(Long coachId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime date,
                              Integer hours) {
}
