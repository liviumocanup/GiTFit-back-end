package com.utm.gitfit.mapper;

import com.utm.gitfit.dto.CardCredentialsDto;
import com.utm.gitfit.model.entities.CardCredentials;
import lombok.NoArgsConstructor;

import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CardCredentialsMapper {
    public static final Function<CardCredentials, CardCredentialsDto> mapToDto = cardCredentials ->
            CardCredentialsDto.builder()
                    .id(cardCredentials.getId())
                    .name(cardCredentials.getName())
                    .lastName(cardCredentials.getLastName())
                    .cardNumber(cardCredentials.getCardNumber())
                    .expiryDate(cardCredentials.getExpiryDate())
                    .build();

    public static final Function<CardCredentialsDto, CardCredentials> mapToEntity = cardCredentialsDto ->
            CardCredentials.builder()
                    .id(cardCredentialsDto.getId())
                    .name(cardCredentialsDto.getName())
                    .lastName(cardCredentialsDto.getLastName())
                    .cardNumber(cardCredentialsDto.getCardNumber())
                    .expiryDate(cardCredentialsDto.getExpiryDate())
                    .build();
}
