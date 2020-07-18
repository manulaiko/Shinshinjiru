package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.view.event.ShowSettingsWindowEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Controller;

/**
 * Avatar context menu controller.
 * ===============================
 *
 * Controller for the Avatar context menu.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
public class AvatarContextMenu {
    @FXML
    private Label settings;

    @FXML
    public void initialize() {
        settings.setOnMouseClicked(e -> ShinshinjiruApplication.publish(new ShowSettingsWindowEvent(this)));
    }
}
