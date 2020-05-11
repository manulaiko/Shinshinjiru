package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.presenter.Details;
import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowDetailsWindowEvent;
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
 * Show Details window handler.
 * ============================
 *
 * Shows the details window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Slf4j
@Component
public class ShowDetailsWindowHandler implements ApplicationListener<ShowDetailsWindowEvent> {
    @Autowired
    private SceneManager sceneManager;

    @Value("classpath:icon.png")
    private Resource icon;

    private TableEntry entry;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(ShowDetailsWindowEvent event) {
        entry = event.getEntry();
        log.debug("Showing details window for " + entry.getEntry().getMedia().getTitle().getUserPreferred());

        Platform.runLater(this::show);
    }

    /**
     * Builds and shows the Details window.
     */
    @SneakyThrows
    private void show() {
        var stage      = new Stage();
        var scene      = sceneManager.buildScene("Details.fxml");
        var controller = (Details) scene.getUserData();

        scene.getStylesheets().add("dark.css");
        controller.setEntry(entry);
        controller.init();

        stage.setScene(scene);
        stage.getIcons().add(new Image(icon.getInputStream()));
        stage.setTitle("Shinshinjiru - " + entry.getName().getValue());
        stage.show();
    }
}
