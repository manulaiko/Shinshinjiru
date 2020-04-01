package com.manulaiko.shinshinjiru.view.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(Stage primaryStage) {
        super(primaryStage);
    }

    public Stage getStage() {
        return (Stage) super.getSource();
    }
}
