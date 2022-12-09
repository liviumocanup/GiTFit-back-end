package com.utm.gitfit.model.request;

import java.time.LocalDate;

public record SuggestionRequest(LocalDate date, Long coachId, String suggestion) {
}
