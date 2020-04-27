package com.manulaiko.shinshinjiru.view.handler;


import com.manulaiko.shinshinjiru.view.event.AlertEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Alert handler.
 * ==============
 *
 * Shows an alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class AlertHandler<T extends AlertEvent> implements ApplicationListener<T> {
    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(T event) {
        var alert = new Alert(event.getType(), event.getMessage(), ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().getStylesheets().add("dark.css");

        alert.show();
    }
}
