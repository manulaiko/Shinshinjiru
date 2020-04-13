package com.manulaiko.shinshinjiru.oauth;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
public class OAuthServer {
    private static final Logger log = LoggerFactory.getLogger(OAuthServer.class);

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
    }

    /**
     * Creates the HttpServer.
     */
    private void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext(endpoint, authCallback);
        } catch (Exception e) {
            System.out.println("Couldn't start HttpServer! " + e.getMessage());
            e.printStackTrace();
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
