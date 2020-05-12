package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Post user lists initialized event.
 * ==================================
 *
 * Fired when the user lists have been initialized.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class PostUserListsInitializedEvent extends ApplicationEvent {
    @Getter
    private final MediaListCollection lists;

    public PostUserListsInitializedEvent(Object source, MediaListCollection lists) {
        super(source);

        this.lists = lists;
    }
}
