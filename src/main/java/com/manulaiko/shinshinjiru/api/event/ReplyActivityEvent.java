package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.ListActivity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Reply activity event.
 * =====================
 *
 * Fired to reply to an activity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SuppressWarnings("java:S1948")
public class ReplyActivityEvent extends ApplicationEvent {
    @Getter
    private final ListActivity activity;

    @Getter
    private final String text;

    public ReplyActivityEvent(Object source, ListActivity activity, String text) {
        super(source);

        this.activity = activity;
        this.text = text;
    }
}
