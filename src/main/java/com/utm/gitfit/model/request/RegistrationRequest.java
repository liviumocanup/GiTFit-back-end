package com.utm.gitfit.model.request;

import java.time.LocalDate;

public record RegistrationRequest(String username, String surname, String firstName, String email, String password, LocalDate birthday, AppUserType userType) {
}
