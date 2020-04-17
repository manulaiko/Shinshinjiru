package com.manulaiko.shinshinjiru.oauth;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.APIToken;
import com.manulaiko.shinshinjiru.view.event.InitMainScreenEvent;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class OAuthCallback implements HttpHandler {
    @Value("${oauth.url}")
    private String url;

    @Value("${oauth.message}")
    private String message;

    @Autowired
    private OAuthServer server;

    @Autowired
    private APIService service;

    @Autowired
    private OAuthTokenRequest request;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] params = httpExchange.getRequestURI()
                                      .getQuery()
                                      .split("=");

        if (!params[0].equals("code")) {
            throw new RuntimeException("Invalid OAuth response!");
        }

        log.debug("Received OAuth callback with code " + params[1]);

        var restTemplate = new RestTemplateBuilder().build();

        request.setCode(params[1]);
        log.info("Executing AuthToken request...");
        var response = restTemplate.postForObject(url, request, APIToken.class);
        service.setToken(response);
        log.info("AuthToken received: " + response);

        httpExchange.sendResponseHeaders(200, message.length());
        var out = httpExchange.getResponseBody();
        out.write(message.getBytes());
        out.flush();
        out.close();

        server.stop();
        ShinshinjiruApplication.publish(new InitMainScreenEvent());
    }
}
