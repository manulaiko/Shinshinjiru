package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * User lists initialized event.
 * =============================
 *
 * Event fired when the user's lists have been initialized.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SuppressWarnings("java:S1948")
public class UserListsInitializedEvent extends ApplicationEvent {
    @Getter
    private final MediaListCollection lists;

    public UserListsInitializedEvent(Object source, MediaListCollection lists) {
        super(source);

        this.lists = lists;
    }
}
