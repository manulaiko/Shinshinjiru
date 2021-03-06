package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowLoadingLabelEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.stage.Popup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Main layout.
 * ============
 * <p>
 * Presenter for the MainLayout scene.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
@Slf4j
public class MainLayout {
    @Autowired
    private SceneManager sceneManager;

    @FXML
    private Group avatarCol;

    private final Popup popup = new Popup();

    @SneakyThrows
    @FXML
    public void initialize() {
        var grid = sceneManager.buildScene("avatarContextMenu.fxml");
        popup.getContent().add(grid.getRoot());
        popup.setAutoHide(true);

        avatarCol.setOnMouseClicked(e -> {
            if (popup.isShowing()) {
                popup.hide();

                return;
            }

            Point2D point = avatarCol.localToScreen(0, 0);
            var x = point.getX();
            var y = point.getY() + 100;

            popup.show(avatarCol, x, y);

            log.debug("AvatarCol shown at " + x + ":" + y);
        });
    }

    @FXML
    public void onListButton() {
        Platform.runLater(() -> sceneManager.show("List.fxml"));
    }

    @FXML
    public void onBrowseButton() {
        ShinshinjiruApplication.publish(new ShowLoadingLabelEvent(this));
    }

    @FXML
    public void onTorrentsButton() {
        ShinshinjiruApplication.publish(new ShowLoadingLabelEvent(this));
    }
}
