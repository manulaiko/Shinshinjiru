package com.manulaiko.shinshinjiru.api.event;

import org.springframework.context.ApplicationEvent;

/**
 * Init user event.
 * ================
 *
 * Event to initialize the authenticated user.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InitUserEvent extends ApplicationEvent {
    /**
     * @inheritDoc
     */
    public InitUserEvent(Object source) {
        super(source);
    }
}
