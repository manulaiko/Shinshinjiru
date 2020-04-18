package com.manulaiko.shinshinjiru.oauth.event;

import org.springframework.context.ApplicationEvent;

/**
 * Stop OAuth Server event.
 * ========================
 *
 * Event Fired when the OAuth server should be stopped.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class StopOauthServerEvent extends ApplicationEvent {
    /**
     * @inheritDoc
     */
    public StopOauthServerEvent(Object source) {
        super(source);
    }
}
