package com.manulaiko.shinshinjiru.oauth;

/**
 * OAuth token request.
 * ====================
 *
 * Request body to send to request the token.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class OAuthTokenRequest {
    public final String grant_type    = "authorization_code";
    public final String client_id     = String.valueOf(OAuthServer.CLIENT_ID);
    public final String redirect_uri  = "http://localhost:"+ OAuthServer.PORT + OAuthServer.CALLBACK;
    public final String client_secret = "zP4dlV02hPyhAMRznt69loaMovx2hcjiGVcLhXfF";

    public String code;

    /**
     * Constructor.
     *
     * @param code Callback code.
     */
    public OAuthTokenRequest(String code) {
        this.code = code;
    }
}
