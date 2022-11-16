package com.utm.gitfit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoRequest {
    private Long id;

    private String name;

    private String lastName;
}
