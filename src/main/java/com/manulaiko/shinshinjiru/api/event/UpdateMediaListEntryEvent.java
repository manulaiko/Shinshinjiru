package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Update MediaList entry event.
 * =============================
 *
 * Event fired to update an entry in the user's list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class UpdateMediaListEntryEvent extends ApplicationEvent {
    @Getter
    private final MediaList entry;

    public UpdateMediaListEntryEvent(Object source, MediaList entry) {
        super(source);

        this.entry = entry;
    }
}
