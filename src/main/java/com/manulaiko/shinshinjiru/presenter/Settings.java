package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
import com.manulaiko.shinshinjiru.view.event.ConfirmAlertEvent;
import com.manulaiko.shinshinjiru.view.event.ErrorAlertEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Settings presenter.
 * ===================
 *
 * Presenter for the settings window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class Settings {
    @Autowired
    private APIService api;

    @Autowired
    private FileBasedConfiguration config;

    @FXML
    private Label userName;

    @FXML
    private ImageView userAvatar;

    @FXML
    private Label syncDate;

    @FXML
    private Label libraryPath;

    @FXML
    public void initialize() {
        userName.setText(api.getUser().getName());
        userAvatar.setImage(new Image(api.getUser().getAvatar().getMedium()));
        syncDate.setText(config.getString("app.lastSync"));
        libraryPath.setText(config.getString("app.libraryPath"));
    }

    @FXML
    public void onLogoutButton(ActionEvent actionEvent) {
        config.setProperty("api.token", "");
        ShinshinjiruApplication.publish(new ConfirmAlertEvent(this, "Successfully logged out, restart the app to login again."));
    }

    @FXML
    public void onSyncButton(ActionEvent actionEvent) {
        syncDate.setText("Syncing...");
        ShinshinjiruApplication.publish(new InitUserEvent(this));
    }

    @FXML
    public void onLibraryButton(ActionEvent actionEvent) {
        var dc = new DirectoryChooser();
        dc.setTitle("Choose the Media Library directory.");
        dc.setInitialDirectory(new File(config.getString("app.libraryPath")));
        var path = dc.showDialog(new Stage());

        if (path == null) {
            ShinshinjiruApplication.publish(new ErrorAlertEvent(this, "You need to select the path to the media library."));

            return;
        }

        config.setProperty("app.libraryPath", path.getAbsolutePath());
        libraryPath.setText(config.getString("app.libraryPath"));
    }

    /**
     * Update last sync date.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userListsInitializedHandler(UserListsInitializedEvent event) {
        if (syncDate == null) {
            // not initialized yet.
            return;
        }

        Platform.runLater(() -> syncDate.setText(config.getString("app.lastSync")));
    }
}
