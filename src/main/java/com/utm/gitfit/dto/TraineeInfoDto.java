package com.utm.gitfit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TraineeInfoDto {

    private Long id;

    private Double weight;

    private Double height;

    private Double fatRatio;

    private String suggestions;

    private String diet;

    private LocalDateTime lastUpdated;
}
