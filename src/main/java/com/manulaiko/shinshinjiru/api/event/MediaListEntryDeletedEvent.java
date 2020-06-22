package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * MediaList entry deleted event.
 * ==============================
 *
 * Event fired when a list entry is deleted.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SuppressWarnings("java:S1948")
public class MediaListEntryDeletedEvent extends ApplicationEvent {
    @Getter
    private final MediaList entry;

    public MediaListEntryDeletedEvent(Object source, MediaList entry) {
        super(source);

        this.entry = entry;
    }
}
