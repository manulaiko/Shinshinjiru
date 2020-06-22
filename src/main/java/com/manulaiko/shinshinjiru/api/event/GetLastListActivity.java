package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.ListActivity;
import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.function.Consumer;

/**
 * Get last ListActivity event.
 * ============================
 *
 * Loads the last ListActivity from the specified media.
 *
 * @author Manulaiko.
 */
@SuppressWarnings("java:S1948")
public class GetLastListActivity extends ApplicationEvent {
    @Getter
    private final MediaList entry;

    @Getter
    private final Consumer<ListActivity> callback;

    public GetLastListActivity(Object source, MediaList entry, Consumer<ListActivity> callback) {
        super(source);

        this.entry = entry;
        this.callback = callback;
    }
}
