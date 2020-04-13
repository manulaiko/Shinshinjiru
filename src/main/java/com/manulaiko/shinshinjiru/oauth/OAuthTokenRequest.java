package com.manulaiko.shinshinjiru.oauth;

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
public class OAuthTokenRequest {
    @Value("${oauth.request.grant-type}")
    public String grant_type;

    @Value("${oauth.request.client-id}")
    public String client_id;

    @Value("${oauth.request.redirect-uri}")
    public String redirect_uri;

    @Value("${oauth.request.client-secret}")
    public String client_secret;

    public String code;

    public void setCode(String code) {
        this.code = code;
    }
}
