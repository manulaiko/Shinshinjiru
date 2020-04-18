package com.manulaiko.shinshinjiru.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

/**
 * API Token.
 * ==========
 *
 * Represents the token that will be used to auth against AniList api.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class APIToken {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
