package com.manulaiko.shinshinjiru.view;

import com.manulaiko.shinshinjiru.exception.UnableToLoadSceneException;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
    private String rootScene;

    @Value("classpath:icon.png")
    private Resource icon;

    /**
     * Shows the window with the root scene.
     */
    public void show() {
        Platform.runLater(this::doShow);
    }

    /**
     * Shows the root scene in the FX thread.
     */
    @SneakyThrows
    private void doShow() {
        var scene = scenes.computeIfAbsent(this.getRootScene(), this::buildScene);
        scene.getStylesheets().addAll("dark.css");

        log.debug("Showing root scene...");
        this.getRootStage().setScene(scene);
        this.rootStage.setTitle("Shinshinjiru");
        this.getRootStage().show();
        this.rootStage.getIcons().add(new Image(icon.getInputStream()));
    }

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
        var subScene = scenes.computeIfAbsent(fxml, this::buildScene);

        log.debug("Showing scene for " + fxml + " ("+ subScene +")");

        var scene = this.getRootStage().getScene();
        var root = (BorderPane)scene.getRoot();
        root.setCenter(subScene.getRoot());
    }

    /**
     * Builds and returns a new scene.
     *
     * @param fxml Scene's fxml.
     *
     * @return New scene.
     */
    public Scene buildScene(String fxml) {
        log.debug("Building scene for " + fxml);
        try {
            var loader = new ContextAwareFXMLLoader(fxml);
            var parent = (Parent) loader.load();

            var scene = new Scene(parent);
            scene.setUserData(loader.getController());

            return scene;
        } catch (IOException e) {
            log.warn("Couldn't load scene!", e);

            throw new UnableToLoadSceneException(e);
        }
    }
}
