package com.manulaiko.shinshinjiru;

import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
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
    private static final Logger                  log = LoggerFactory.getLogger(ShinshinjiruApplication.class);
    private static       ShinshinjiruApplication instance;

    /**
     * Opens an url in the browser.
     *
     * @param url URL to open.
     */
    public static void openInBrowser(String url) {
        if (instance == null) {
            log.warn("Application hasn't be initialized yet!");

            return;
        }

        instance.getHostServices().showDocument(url);
    }

    /**
     * Returns the application context.
     *
     * @return Application context.
     *
     * @throws RuntimeException
     */
    public static ApplicationContext getApplicationContext() {
        if (instance == null) {
            throw new RuntimeException("Application hasn't be initialized yet!");
        }

        return instance.applicationContext;
    }

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
        applicationContext               = new SpringApplicationBuilder(ShinshinjiruApplication.class).run();
        ShinshinjiruApplication.instance = this;
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
