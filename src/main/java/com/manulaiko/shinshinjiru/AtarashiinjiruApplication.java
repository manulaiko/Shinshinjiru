package com.manulaiko.shinshinjiru;

import com.manulaiko.shinshinjiru.view.event.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AtarashiinjiruApplication extends Application {
    public static void main(String[] args) {
        Application.launch(AtarashiinjiruApplication.class, args);
    }

    private ConfigurableApplicationContext applicationContext;

    /**
     * @inheritDoc
     */
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(AtarashiinjiruApplication.class).run();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void stop() {
        applicationContext.stop();
        Platform.exit();
    }

}
