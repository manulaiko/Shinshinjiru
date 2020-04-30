package com.manulaiko.shinshinjiru.view.event;

import org.springframework.context.ApplicationEvent;

/**
 * Show loading label event.
 * =========================
 *
 * Event fired when the application must show the loading label.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShowLoadingLabelEvent extends ApplicationEvent {
    public ShowLoadingLabelEvent(Object source) {
        super(source);
    }
}
