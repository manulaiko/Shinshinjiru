package com.manulaiko.shinshinjiru.oauth;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.oauth.event.OauthServerStartedEvent;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * OAuth web server.
 * =================
 *
 * Serves the OAuth callback.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
@Slf4j
@SuppressWarnings("java:S1191")
public class OAuthServer {
    private HttpServer server;

    @Autowired
    private OAuthCallback authCallback;

    @Value("${oauth.port}")
    private Integer port;

    @Value("${oauth.endpoint}")
    private String endpoint;

    /**
     * Starts the HttpServer.
     */
    public void start() {
        log.info("Starting OAuthServer...");
        if (server == null) {
            createServer();
        }

        server.start();
        log.debug("OAuthServer started at port " + port);

        ShinshinjiruApplication.publish(new OauthServerStartedEvent(this));
    }

    /**
     * Creates the HttpServer.
     */
    private void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext(endpoint, authCallback);
        } catch (Exception e) {
            log.error("Couldn't start HttpServer!", e);
        }
    }

    /**
     * Stops the HttpServer.
     */
    public void stop() {
        log.info("Stopping OAuthServer...");
        server.stop(0);
        log.debug("OAuthServer stopped.");
    }
}
