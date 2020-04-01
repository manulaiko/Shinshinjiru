package com.manulaiko.shinshinjiru.api;

/**
 * API Token.
 * ==========
 *
 * Represents the token that will be used to auth against AniList api.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class APIToken {
    private String token_type;
    private int    expires_in;
    private String access_token;
    private String refresh_token;

    public String getTokenType() {
        return token_type;
    }

    public int getExpiresIn() {
        return expires_in;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }
}
