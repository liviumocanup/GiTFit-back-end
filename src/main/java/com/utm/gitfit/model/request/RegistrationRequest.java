package com.utm.gitfit.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record RegistrationRequest(@NotBlank(message = "Username is mandatory") String username,
                                  @NotBlank(message = "Surname is mandatory") String surname,
                                  @NotBlank(message = "First name is mandatory") String firstName,
                                  @Email String email,
                                  @NotBlank(message = "Password is mandatory") String password,
                                  @NotNull LocalDate birthday,
                                  AppUserType userType) {
}
