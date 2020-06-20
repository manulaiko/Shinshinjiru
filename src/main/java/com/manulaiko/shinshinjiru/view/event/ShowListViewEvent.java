package com.manulaiko.shinshinjiru.view.event;

import org.springframework.context.ApplicationEvent;

/**
 * Show List view event.
 * =====================
 * <p>
 * Event fired to show the List view.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShowListViewEvent extends ApplicationEvent {
    public ShowListViewEvent(Object source) {
        super(source);
    }
}
