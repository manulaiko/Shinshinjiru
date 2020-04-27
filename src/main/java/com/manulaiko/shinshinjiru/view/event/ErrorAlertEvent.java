package com.manulaiko.shinshinjiru.view.event;

import javafx.scene.control.Alert;

/**
 * Error Alert event.
 * ==================
 *
 * Shows an error alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ErrorAlertEvent extends AlertEvent {
    public ErrorAlertEvent(Object source, String message) {
        super(source, Alert.AlertType.ERROR, message);
    }
}
