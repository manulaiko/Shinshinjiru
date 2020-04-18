package com.manulaiko.shinshinjiru.oauth.handler;

import com.manulaiko.shinshinjiru.oauth.OAuthServer;
import com.manulaiko.shinshinjiru.oauth.event.StopOauthServerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Stop OAuth server handler.
 * ==========================
 *
 * Stops the OAuth server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class StopOauthServerHandler implements ApplicationListener<StopOauthServerEvent> {
    @Autowired
    private OAuthServer server;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(StopOauthServerEvent event) {
        server.stop();
    }
}
