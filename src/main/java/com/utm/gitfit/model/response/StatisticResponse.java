package com.utm.gitfit.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class StatisticResponse {
    private LocalDate date;
    private Double weight;
    private Double height;
    private Double fatRatio;
    private Set<SuggestionResponse> suggestions;
    private String diet;
}
