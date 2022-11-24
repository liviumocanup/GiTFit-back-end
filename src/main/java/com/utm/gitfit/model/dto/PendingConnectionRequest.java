package com.utm.gitfit.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PendingConnectionRequest {

    private Long id;

    private UserDto sourceUser;

    private LocalDateTime createdAt;
}
