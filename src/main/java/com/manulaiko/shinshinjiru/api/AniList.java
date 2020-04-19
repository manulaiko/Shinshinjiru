package com.manulaiko.shinshinjiru.api;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.api.event.InitUserListsEvent;
import com.manulaiko.shinshinjiru.api.event.UserInitializedEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import com.manulaiko.shinshinjiru.api.model.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
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
public class AniList {
    @Autowired
    private APIService api;

    /**
     * The user instance.
     */
    private User user;

    /**
     * The user lists.
     */
    private MediaListCollection lists;

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
     * Returns the lists.
     *
     * @return Authenticated user's lists.
     */
    public MediaListCollection getLists() {
        log.info("Retrieving lists");
        if (lists == null) {
            ShinshinjiruApplication.publish(new InitUserListsEvent(this));
        }

        log.info("Lists loaded!");
        return lists;
    }

    /**
     * Sets the User instance when the user is initialized.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userInitializedHandler(UserInitializedEvent event) {
        log.info("User initialized.");
        user = event.getUser();
    }

    /**
     * Sets the User lists when they are initialized.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userListsInitializedHandler(UserListsInitializedEvent event) {
        log.debug("User lists initialized!");
        lists = event.getLists();
    }
}
