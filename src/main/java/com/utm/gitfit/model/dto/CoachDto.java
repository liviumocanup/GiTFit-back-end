package com.utm.gitfit.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoachDto {

    private Long id;

    private String name;

    private String lastName;

    private String email;
}
