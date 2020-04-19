package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserListsInitializedEvent extends ApplicationEvent {
    private final MediaListCollection lists;

    public UserListsInitializedEvent(Object source, MediaListCollection mediaListCollection) {
        super(source);

        lists = mediaListCollection;
    }
}
