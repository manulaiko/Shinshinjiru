package com.manulaiko.shinshinjiru.oauth.event;

import org.springframework.context.ApplicationEvent;

/**
 * Start OAuth Server event.
 * =========================
 *
 * Event Fired when the OAuth server should be started.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class StartOauthServerEvent extends ApplicationEvent {
    public StartOauthServerEvent(Object source) {
        super(source);
    }
}
