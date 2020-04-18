package com.manulaiko.shinshinjiru;

import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
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
@Slf4j
public class ShinshinjiruApplication extends Application {
    private static ShinshinjiruApplication instance;

    /**
     * Opens an url in the browser.
     *
     * @param url URL to open.
     */
    public static void openInBrowser(String url) {
        instance.getHostServices().showDocument(url);
    }

    /**
     * Returns the application context.
     *
     * @return Application context.
     */
    public static ApplicationContext getApplicationContext() {
        return instance.applicationContext;
    }

    /**
     * Publishes an event in the app context.
     *
     * @param event Event to publish.
     */
    public static void publish(ApplicationEvent event) {
        instance.applicationContext.publishEvent(event);
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
    public void start(Stage stage) {
        var sceneManager = applicationContext.getBean(SceneManager.class);
        sceneManager.setRootStage(stage);
        sceneManager.setRootScene("MainLayout.fxml");

        applicationContext.publishEvent(new InitLoadingScreenEvent());
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
