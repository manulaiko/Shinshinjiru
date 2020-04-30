package com.manulaiko.shinshinjiru.view.event;

import org.springframework.context.ApplicationEvent;

/**
 * Init main screen event.
 * =======================
 *
 * Event fired to initialize the main screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InitMainScreenEvent extends ApplicationEvent {
    public InitMainScreenEvent(Object source) {
        super(source);
    }
}
