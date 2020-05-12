package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.api.event.PostUserListsInitializedEvent;
import com.manulaiko.shinshinjiru.view.SceneManager;
import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Post user lists initialized event.
 * ===================================
 *
 * Shows the lists tab.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class PostUserListsInitializedHandler implements ApplicationListener<PostUserListsInitializedEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(PostUserListsInitializedEvent event) {
        log.debug("Loading list...");
        Platform.runLater(() -> sceneManager.show("List.fxml"));
    }
}
