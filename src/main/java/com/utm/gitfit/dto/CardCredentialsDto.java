package com.utm.gitfit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.YearMonth;

@Data
@Builder
public class CardCredentialsDto {

    private Long id;

    private String name;

    private String lastName;

    private Long cardNumber;

    private YearMonth expiryDate;
}
