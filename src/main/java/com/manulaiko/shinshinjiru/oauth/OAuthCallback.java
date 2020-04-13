package com.manulaiko.shinshinjiru.oauth;

import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.APIToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * OAuth callback.
 * ===============
 *
 * Executes the callback received from the OAuth server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class OAuthCallback implements HttpHandler {
    private static final Logger log      = LoggerFactory.getLogger(OAuthCallback.class);
    public static final  String URL      = "https://anilist.co/api/v2/oauth/token";
    private static final String RESPONSE = "Done! You can now close this tab.";

    @Autowired
    private OAuthServer server;

    @Autowired
    private APIService service;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] params = httpExchange.getRequestURI()
                                      .getQuery()
                                      .split("=");

        if (!params[0].equals("code")) {
            throw new RuntimeException("Invalid OAuth response!");
        }

        log.debug("Received OAuth callback with code " + params[1]);

        var request      = new OAuthTokenRequest(params[1]);
        var restTemplate = new RestTemplateBuilder().build();

        log.info("Executing AuthToken request...");
        var response = restTemplate.postForObject(URL, request, APIToken.class);
        service.setToken(response);
        log.info("AuthToken received: " + response);


        httpExchange.sendResponseHeaders(200, RESPONSE.length());
        var out = httpExchange.getResponseBody();
        out.write(RESPONSE.getBytes());
        out.flush();
        out.close();

        server.stop();
    }
}
