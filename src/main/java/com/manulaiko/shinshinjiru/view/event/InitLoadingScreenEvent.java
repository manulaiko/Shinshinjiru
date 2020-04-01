package com.manulaiko.shinshinjiru.view.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Init loading screen event.
 * ==========================
 *
 * Event fired to initialize the loading screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InitLoadingScreenEvent extends ApplicationEvent {
    /**
     * Constructor.
     *
     * @param primaryStage Root stage.
     */
    public InitLoadingScreenEvent(Stage primaryStage) {
        super(primaryStage);
    }
}
