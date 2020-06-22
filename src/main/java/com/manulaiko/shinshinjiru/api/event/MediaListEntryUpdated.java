package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * MediaList entry updated event.
 * ==============================
 * <p>
 * Fired when a MediaList entry has been updated.
 *
 * @author Manulaiko <manuliko@gmail.com>
 */
@SuppressWarnings("java:S1948")
public class MediaListEntryUpdated extends ApplicationEvent {
    @Getter
    private final MediaList entry;

    public MediaListEntryUpdated(Object source, MediaList entry) {
        super(source);

        this.entry = entry;
    }
}
