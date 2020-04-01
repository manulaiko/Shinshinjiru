package com.manulaiko.shinshinjiru.view.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class InitLoadingScreenEvent extends ApplicationEvent {
    public InitLoadingScreenEvent(Stage primaryStage) {
        super(primaryStage);
    }

    public Stage getStage() {
        return (Stage) super.getSource();
    }
}
