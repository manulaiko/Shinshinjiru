package com.manulaiko.shinshinjiru.view.event;

import javafx.scene.control.Alert;

/**
 * Info Alert event.
 * =================
 *
 * Shows an info alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InfoAlertEvent extends AlertEvent {
    public InfoAlertEvent(Object source, String message) {
        super(source, Alert.AlertType.INFORMATION, message);
    }
}
