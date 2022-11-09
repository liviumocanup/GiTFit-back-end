package com.utm.gitfit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientDto {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private LocalDate birthday;

    private Long bankAccountId;

    //or Id, depending on front?
    private String coachName;

    private TraineeInfoDto traineeInfo;
}
