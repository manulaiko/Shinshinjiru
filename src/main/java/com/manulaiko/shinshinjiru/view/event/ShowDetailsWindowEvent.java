package com.manulaiko.shinshinjiru.view.event;

import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Show Details window event.
 * ==========================
 *
 * Event for showing the details window of an item.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShowDetailsWindowEvent extends ApplicationEvent {
    @Getter
    private final TableEntry entry;

    public ShowDetailsWindowEvent(Object source, TableEntry entry) {
        super(source);

        this.entry = entry;
    }
}
