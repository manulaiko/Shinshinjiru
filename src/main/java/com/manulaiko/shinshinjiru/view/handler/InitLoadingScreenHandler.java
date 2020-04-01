package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InitLoadingScreenHandler implements ApplicationListener<InitLoadingScreenEvent> {
    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitLoadingScreenEvent event) {
        try {
            var fxmlLoader = new FXMLLoader(new ClassPathResource("LoadingScreen.fxml").getURL());
            var parent     = (Parent) fxmlLoader.load();

            var stage = event.getStage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.toFront();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
