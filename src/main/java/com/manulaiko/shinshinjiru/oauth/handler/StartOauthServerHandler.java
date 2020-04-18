package com.manulaiko.shinshinjiru.oauth.handler;

import com.manulaiko.shinshinjiru.oauth.OAuthServer;
import com.manulaiko.shinshinjiru.oauth.event.StartOauthServerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Start OAuth server handler.
 * ===========================
 *
 * Starts the OAuth server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class StartOauthServerHandler implements ApplicationListener<StartOauthServerEvent> {
    @Autowired
    private OAuthServer server;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(StartOauthServerEvent event) {
        server.start();
    }
}
