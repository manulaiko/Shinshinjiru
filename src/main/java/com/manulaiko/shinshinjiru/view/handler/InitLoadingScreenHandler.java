package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.ContextAwareFXMLLoader;
import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Init loading screen handler.
 * ============================
 *
 * Initializes the loading screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class InitLoadingScreenHandler implements ApplicationListener<InitLoadingScreenEvent> {
    private static final Logger log = LoggerFactory.getLogger(InitLoadingScreenHandler.class);

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitLoadingScreenEvent event) {
        try {
            log.debug("Loading LoadingScreen...");
            var fxmlLoader = new ContextAwareFXMLLoader("LoadingScreen.fxml");
            var parent     = (Parent) fxmlLoader.load();
            var stage      = (Stage) event.getSource();

            stage.setScene(new Scene(parent));
            stage.show();
            stage.toFront();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
