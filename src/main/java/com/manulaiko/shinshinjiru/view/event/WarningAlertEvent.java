package com.manulaiko.shinshinjiru.view.event;

import javafx.scene.control.Alert;

/**
 * Warning Alert event.
 * ====================
 *
 * Shows a warning alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class WarningAlertEvent extends AlertEvent {
    public WarningAlertEvent(Object source, String message) {
        super(source, Alert.AlertType.WARNING, message);
    }
}
