package com.utm.gitfit.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ScheduleRequest(Long coachId, LocalDateTime date, Integer hours) {
}
