package com.utm.gitfit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CoachDto {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private LocalDate birthday;

    private Long bankAccountId;

    //or Ids, depending on front?
    private List<String> clients;
}
