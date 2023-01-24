package com.utm.gitfit.model.response;

import com.utm.gitfit.model.dto.BillingDetailsDto;
import com.utm.gitfit.model.dto.ClientDto;
import com.utm.gitfit.model.dto.ScheduledSessionDto;
import com.utm.gitfit.model.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class CoachResponse {
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

    private Set<ClientDto> clients;

    private Set<ScheduledSessionDto> scheduledSessions;

    private Double ratePerHour;

    private String gymAddress;

    private String aboutMe;
}
