package com.manulaiko.shinshinjiru.api.event;

import org.springframework.context.ApplicationEvent;

/**
 * Init user lists event.
 * ======================
 *
 * Event to initialize the user lists.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InitUserListsEvent extends ApplicationEvent {
    public InitUserListsEvent(Object source) {
        super(source);
    }
}
