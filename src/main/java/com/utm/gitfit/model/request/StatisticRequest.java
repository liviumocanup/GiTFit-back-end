package com.utm.gitfit.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StatisticRequest {
    private LocalDate date;
    private Double weight;
    private Double height;
    private Double fatRatio;
    private String diet;
}
