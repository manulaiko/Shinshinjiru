package com.manulaiko.shinshinjiru;

import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Shinshinjiru Application.
 * =========================
 *
 * Application bootstrap.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SpringBootApplication
public class ShinshinjiruApplication extends Application {
    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(ShinshinjiruApplication.class, args);
    }

    private ConfigurableApplicationContext applicationContext;

    /**
     * @inheritDoc
     */
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(ShinshinjiruApplication.class).run();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new InitLoadingScreenEvent(primaryStage));
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
