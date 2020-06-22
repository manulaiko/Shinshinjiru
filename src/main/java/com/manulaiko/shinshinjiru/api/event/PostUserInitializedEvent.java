package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Post user initialization event.
 * ===============================
 *
 * Event fired after the user has been completely initialized.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SuppressWarnings("java:S1948")
public class PostUserInitializedEvent extends ApplicationEvent {
    @Getter
    private final User user;

    public PostUserInitializedEvent(Object source, User user) {
        super(source);

        this.user = user;
    }
}
