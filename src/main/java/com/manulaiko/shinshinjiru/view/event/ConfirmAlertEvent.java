package com.manulaiko.shinshinjiru.view.event;

import javafx.scene.control.Alert;

/**
 * Confirm Alert event.
 * ====================
 *
 * Shows a confirm alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ConfirmAlertEvent extends AlertEvent {
    public ConfirmAlertEvent(Object source, String message) {
        super(source, Alert.AlertType.CONFIRMATION, message);
    }
}
