package com.manulaiko.shinshinjiru.view.event;

import javafx.scene.control.Alert;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Alert event.
 * ============
 *
 * Shows an alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class AlertEvent extends ApplicationEvent {
    @Getter
    private final Alert.AlertType type;

    @Getter
    private final String message;

    public AlertEvent(Object source, Alert.AlertType type, String message) {
        super(source);


        this.type    = type;
        this.message = message;
    }
}
