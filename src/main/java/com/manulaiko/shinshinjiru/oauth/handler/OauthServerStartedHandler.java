package com.manulaiko.shinshinjiru.oauth.handler;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.oauth.event.OauthServerStartedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * OAuth server started handler.
 * =============================
 *
 * Handles the logic after starting the server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class OauthServerStartedHandler implements ApplicationListener<OauthServerStartedEvent> {
    @Value("{oauth.open-url}")
    private String openUrl;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(OauthServerStartedEvent event) {
        ShinshinjiruApplication.openInBrowser(openUrl);
    }
}
