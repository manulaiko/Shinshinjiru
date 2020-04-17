package com.manulaiko.shinshinjiru.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * OAuth token request.
 * ====================
 *
 * Request body to send to request the token.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Data
public class OAuthTokenRequest {
    @Value("${oauth.request.grant-type}")
    @JsonProperty("grant_type")
    public String grantType;

    @Value("${oauth.request.client-id}")
    @JsonProperty("client_id")
    public String clientId;

    @Value("${oauth.request.redirect-uri}")
    @JsonProperty("redirect_uri")
    public String redirectUri;

    @Value("${oauth.request.client-secret}")
    @JsonProperty("client_secret")
    public String clientSecret;

    public String code;
}
