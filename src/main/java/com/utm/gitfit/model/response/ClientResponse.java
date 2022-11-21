package com.utm.gitfit.model.response;

import com.utm.gitfit.model.entities.Coach;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientResponse {
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private LocalDate birthday;

    private Long bankAccountId;

    private TraineeInfo traineeInfo;

    private Coach coach;
}
