package com.manulaiko.shinshinjiru.api;

import org.springframework.stereotype.Service;

/**
 * API Service.
 * ============
 *
 * Contains the API implementation.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
public class APIService {
    private APIToken token;

    public APIToken getToken() {
        return token;
    }

    public void setToken(APIToken token) {
        this.token = token;
    }
}
