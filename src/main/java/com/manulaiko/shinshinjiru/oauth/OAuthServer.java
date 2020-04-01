package com.manulaiko.shinshinjiru.oauth;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * OAuth web server.
 * =================
 *
 * Serves the OAuth callback.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class OAuthServer {
    private static final Logger log           = LoggerFactory.getLogger(OAuthServer.class);
    public static final  int    PORT          = 9876;
    public static final  String CALLBACK      = "/callback";
    public static final  int    CLIENT_ID     = 3310;
    public static final  String RESPONSE_TYPE = "code";

    private HttpServer server;

    /**
     * Returns the OAuth url.
     *
     * @return URL to open in browser.
     */
    public String getUrl() {
        return "https://anilist.co/api/v2/oauth/authorize?client_id=" +
               CLIENT_ID +
               "&response_type=" +
               RESPONSE_TYPE;
    }

    /**
     * Starts the HttpServer.
     */
    public void start() {
        log.info("Starting OAuthServer...");
        if (server == null) {
            createServer();
        }

        server.start();
        log.debug("OAuthServer started at port " + PORT);
    }

    /**
     * Creates the HttpServer.
     */
    private void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext(CALLBACK, new OAuthCallback(this));
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
