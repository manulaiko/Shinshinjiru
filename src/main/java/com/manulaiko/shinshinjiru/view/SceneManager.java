package com.manulaiko.shinshinjiru.view;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Scene manager.
 * ==============
 *
 * Helper class to manage all application scenes.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Data
@Slf4j
public class SceneManager {
    private final Map<String, Scene> scenes = new HashMap<>();

    private Stage rootStage;

    /**
     * Switch to the given scene.
     *
     * @param fxml Scene's fxml to switch the stage to.
     */
    public void show(String fxml) {
        Platform.runLater(() -> this.doShow(fxml));
    }

    /**
     * Performs the scene switch in the FX thread.
     *
     * @param fxml Scene's fxml to switch the stage to.
     */
    private void doShow(String fxml) {
        var scene = scenes.computeIfAbsent(fxml, this::buildScene);

        log.debug("Showing scene for " + scene);
        this.getRootStage().setScene(scene);
        this.getRootStage().show();
    }

    /**
     * Builds and returns a new scene.
     *
     * @param fxml Scene's fxml.
     *
     * @return New scene.
     */
    private Scene buildScene(String fxml) {
        log.debug("Building scene for " + fxml);
        try {
            var loader = new ContextAwareFXMLLoader(fxml);
            var parent = (Parent) loader.load();

            return new Scene(parent);
        } catch (IOException e) {
            log.warn("Couldn't load scene!", e);

            throw new RuntimeException(e);
        }
    }
}
