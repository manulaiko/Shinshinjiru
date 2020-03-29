package com.manulaiko.atarashiinjiru;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<AtarashiinjiruApplication.StageReadyEvent> {
    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(AtarashiinjiruApplication.StageReadyEvent event) {
        try {
            var fxmlLoader = new FXMLLoader(new ClassPathResource("LoadingScreen.fxml").getURL());
            var parent     = (Parent) fxmlLoader.load();

            var stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}