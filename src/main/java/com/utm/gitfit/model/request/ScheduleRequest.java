package com.utm.gitfit.model.request;

import java.time.LocalDateTime;

public record ScheduleRequest(Long coachId, LocalDateTime date, Integer hours) {
}
