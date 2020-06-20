package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.api.event.PostUserInitializedEvent;
import com.manulaiko.shinshinjiru.view.SceneManager;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Post user initialized event.
 * ============================
 *
 * Updates the GUI with user's info.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class PostUserInitializedHandler implements ApplicationListener<PostUserInitializedEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(PostUserInitializedEvent event) {
        log.debug("Updating user info...");
        Platform.runLater(() -> show(event));
    }

    private void show(PostUserInitializedEvent event) {
        var username = (Label)sceneManager.getRootStage().getScene().lookup("#username");
        username.setText(event.getUser().getName());

        var avatar = (ImageView)sceneManager.getRootStage().getScene().lookup("#avatar");
        avatar.setImage(new Image(event.getUser().getAvatar().getMedium()));

        sceneManager.getRootStage().setTitle("Shinshinjiru - "+ event.getUser().getName());
    }
}
