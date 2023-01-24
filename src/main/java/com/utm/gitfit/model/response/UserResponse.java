package com.utm.gitfit.model.response;

import com.utm.gitfit.model.dto.BillingDetailsDto;
import com.utm.gitfit.model.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String username;

    private LocalDate birthday;

    private BillingDetailsDto billingDetails;

    private String password;

    private Role userRole;

    private String saltEdgeIdentifier;
}
