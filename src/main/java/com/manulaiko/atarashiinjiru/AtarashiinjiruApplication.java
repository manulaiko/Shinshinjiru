package com.manulaiko.atarashiinjiru;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
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

    public static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage primaryStage) {
            super(primaryStage);
        }

        public Stage getStage() {
            return (Stage) super.getSource();
        }
    }
}
