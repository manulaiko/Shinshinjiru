package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowSettingsWindowEvent;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Show Settings window handler.
 * =============================
 *
 * Shows the settings window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Slf4j
@Component
public class ShowSettingsWindowHandler implements ApplicationListener<ShowSettingsWindowEvent> {
    @Autowired
    private SceneManager sceneManager;

    @Value("classpath:icon.png")
    private Resource icon;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(ShowSettingsWindowEvent event) {
        log.debug("Showing settings window");

        Platform.runLater(this::show);
    }

    /**
     * Builds and shows the Details window.
     */
    @SneakyThrows
    private void show() {
        var stage      = new Stage();
        var scene      = sceneManager.buildScene("Settings.fxml");

        scene.getStylesheets().add("dark.css");

        stage.setScene(scene);
        stage.getIcons().add(new Image(icon.getInputStream()));
        stage.setTitle("Shinshinjiru - Settings");
        stage.show();
    }
}
