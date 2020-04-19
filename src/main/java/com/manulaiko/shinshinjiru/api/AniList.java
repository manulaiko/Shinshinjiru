package com.manulaiko.shinshinjiru.api;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.api.event.UserInitializedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.User;
import com.manulaiko.shinshinjiru.util.SyncEventListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AniList controller.
 * ===================
 *
 * Handles the anilist api context.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
@Data
public class AniList {
    @Autowired
    private APIService api;

    /**
     * The user instance.
     */
    private User user;

    /**
     * Returns the user.
     *
     * @return The authenticated user.
     */
    public User getUser() {
        if (user == null) {
            ShinshinjiruApplication.publish(new InitUserEvent(this));
        }

        return user;
    }

    /**
     * Sets the User instance when the user is initialized.
     *
     * @param event Fired event.
     */
    @SyncEventListener
    public void userInitializedHandler(UserInitializedEvent event) {
        user = event.getUser();
    }
}
