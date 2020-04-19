package com.manulaiko.shinshinjiru.api.event;

import com.manulaiko.shinshinjiru.api.model.dto.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * User initialized event.
 * =======================
 *
 * Fired when the user has been initialized.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Getter
@Setter
public class UserInitializedEvent extends ApplicationEvent {
    private User user;

    /**
     * Constructor.
     *
     * @param source Object that fired the event.
     * @param user   Initialized user.
     */
    public UserInitializedEvent(Object source, User user) {
        super(source);

        this.setUser(user);
    }
}
