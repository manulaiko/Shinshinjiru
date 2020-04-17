package com.manulaiko.shinshinjiru.view.event;

import org.springframework.context.ApplicationEvent;

/**
 * Init loading screen event.
 * ==========================
 *
 * Event fired to initialize the loading screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InitLoadingScreenEvent extends ApplicationEvent {
    /**
     * Constructor.
     */
    public InitLoadingScreenEvent() {
        super(new Object());
    }
}
