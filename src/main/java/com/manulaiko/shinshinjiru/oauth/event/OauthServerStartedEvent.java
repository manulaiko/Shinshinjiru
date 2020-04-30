package com.manulaiko.shinshinjiru.oauth.event;

import org.springframework.context.ApplicationEvent;

/**
 * OAuth Server started event.
 * ===========================
 *
 * Event Fired when the OAuth server has been started.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class OauthServerStartedEvent extends ApplicationEvent {
    public OauthServerStartedEvent(Object source) {
        super(source);
    }
}
