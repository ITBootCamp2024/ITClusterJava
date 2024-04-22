package com.ua.itclusterjava2024.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @JsonProperty(value = "access_token", required = true)
    private String accessToken;

    @JsonProperty(value = "refresh_token", required = true)
    private String refreshToken;

    @JsonProperty(required = true)
    private String role;
}
