package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.event.AlertEvent;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Alert handler.
 * ==============
 * <p>
 * Shows an alert.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class AlertHandler<T extends AlertEvent> implements ApplicationListener<T> {
    @Value("classpath:icon.png")
    private Resource icon;

    private T event;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(T event) {
        this.event = event;
        Platform.runLater(this::show);
    }

    /**
     * Performs the popup show in the FX thread.
     */
    @SneakyThrows
    private void show() {
        var alert = new Alert(event.getType(), event.getMessage(), ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().getStylesheets().add("dark.css");
        alert.setTitle("Shinshinjiru");
        (
                (Stage) alert.getDialogPane()
                        .getScene()
                        .getWindow()
        )
                .getIcons()
                .add(new Image(icon.getInputStream()));

        alert.show();
    }
}
