package com.manulaiko.shinshinjiru.view.event;

import org.springframework.context.ApplicationEvent;

/**
 * Show settings window event.
 * ===========================
 *
 * Event fired to show the settings window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShowSettingsWindowEvent extends ApplicationEvent {
    public ShowSettingsWindowEvent(Object source) {
        super(source);
    }
}
