package com.manulaiko.shinshinjiru.api;

/**
 * API Service.
 * ============
 *
 * Contains the API implementation.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class APIService {
    private APIToken token;

    public APIToken getToken() {
        return token;
    }

    public void setToken(APIToken token) {
        this.token = token;
    }
}
