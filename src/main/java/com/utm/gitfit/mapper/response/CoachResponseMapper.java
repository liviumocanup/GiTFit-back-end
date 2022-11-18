package com.utm.gitfit.mapper.response;

import static lombok.AccessLevel.PRIVATE;

import com.utm.gitfit.model.entities.Coach;
import com.utm.gitfit.model.response.CoachResponse;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = PRIVATE)
public class CoachResponseMapper {

    public static final Function<Coach, CoachResponse> mapToResponse = coach ->
            CoachResponse.builder()
                    .id(coach.getId())
                    .name(coach.getName())
                    .lastName(coach.getLastName())
                    .email(coach.getEmail())
                    .username(coach.getUsername())
                    .password(coach.getPassword())
                    .birthday(coach.getBirthday())
                    .bankAccountId(coach.getBankAccountId())
                    .build();
}
