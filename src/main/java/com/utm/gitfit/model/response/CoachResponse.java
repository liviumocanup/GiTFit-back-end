package com.utm.gitfit.model.response;

import com.utm.gitfit.model.entities.Client;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CoachResponse {
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private LocalDate birthday;

    private Long bankAccountId;

    private List<Client> clients;
}
