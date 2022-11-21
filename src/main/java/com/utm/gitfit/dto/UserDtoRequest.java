package com.utm.gitfit.dto;

import lombok.Data;

@Data
public class UserDtoRequest {
    private Long id;

    private String name;

    private String lastName;
}
