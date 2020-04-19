package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.api.event.UserInitializedEvent;
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
 * User initialized event.
 * =======================
 *
 * Updates the GUI with user's info.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class UserInitializedHandler implements ApplicationListener<UserInitializedEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(UserInitializedEvent event) {
        log.debug("Updating user info...");
        Platform.runLater(() -> {
            var username = (Label)sceneManager.getRootStage().getScene().lookup("#username");
            username.setText(event.getUser().getName());

            var avatar = (ImageView)sceneManager.getRootStage().getScene().lookup("#avatar");
            avatar.setImage(new Image(event.getUser().getAvatar().getMedium()));

            sceneManager.show("List.fxml");
        });
    }
}
