package com.utm.gitfit.mapper;

import com.utm.gitfit.dto.CoachDto;
import com.utm.gitfit.model.entities.Coach;
import lombok.NoArgsConstructor;

import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CoachMapper {
    public static final Function<Coach, CoachDto> mapToDto = coach ->
            CoachDto.builder()
                    .id(coach.getId())
                    .name(coach.getName())
                    .lastName(coach.getLastName())
                    .email(coach.getEmail())
                    .username(coach.getUsername())
                    .password(coach.getPassword())
                    .birthday(coach.getBirthday())
                    .bankAccountId(coach.getBankAccountId())
                    .build();

    public static final Function<CoachDto, Coach> mapToEntity = coachDto ->
            Coach.builder()
                    .id(coachDto.getId())
                    .name(coachDto.getName())
                    .lastName(coachDto.getLastName())
                    .email(coachDto.getEmail())
                    .username(coachDto.getUsername())
                    .password(coachDto.getPassword())
                    .birthday(coachDto.getBirthday())
                    .bankAccountId(coachDto.getBankAccountId())
                    .build();
}
