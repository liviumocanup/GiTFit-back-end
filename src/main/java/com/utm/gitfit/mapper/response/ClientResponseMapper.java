package com.utm.gitfit.mapper.response;

import static lombok.AccessLevel.PRIVATE;

import com.utm.gitfit.model.entities.Client;
import com.utm.gitfit.model.response.ClientResponse;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = PRIVATE)
public class ClientResponseMapper {
    public static final Function<Client, ClientResponse> mapToResponse = client ->
            ClientResponse.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .username(client.getUsername())
                    .password(client.getPassword())
                    .birthday(client.getBirthday())
                    .bankAccountId(client.getBankAccountId())
                    .traineeInfo(client.getTraineeInfo())
                    .coach(client.getCoach())
                    .build();
}
