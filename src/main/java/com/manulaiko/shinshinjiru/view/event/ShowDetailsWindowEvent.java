package com.manulaiko.shinshinjiru.view.event;

import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import com.manulaiko.shinshinjiru.presenter.lists.TableList;
import lombok.Data;
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
@Getter
public class ShowDetailsWindowEvent extends ApplicationEvent {
    private final TableEntry entry;

    /**
     * Constructor.
     *
     * @param source Instance that fired the event.
     * @param entry  Entry to display.
     */
    public ShowDetailsWindowEvent(Object source, TableEntry entry) {
        super(source);

        this.entry = entry;
    }
}
