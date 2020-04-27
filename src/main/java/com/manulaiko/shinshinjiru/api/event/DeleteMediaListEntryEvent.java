package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import com.manulaiko.shinshinjiru.presenter.details.ListEntry;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Delete MediaList entry event.
 * =============================
 *
 * Event fired to delete an entry from the user's media list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class DeleteMediaListEntryEvent extends ApplicationEvent {
    @Getter
    private MediaList entry;

    public DeleteMediaListEntryEvent(Object source, MediaList entry) {
        super(source);

        this.entry = entry;
    }
}
