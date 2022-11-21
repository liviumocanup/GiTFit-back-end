package com.utm.gitfit.model.response;

import java.util.List;

public record JwtResponse(Long id, String email, List<String> roles, String accessToken) {
}