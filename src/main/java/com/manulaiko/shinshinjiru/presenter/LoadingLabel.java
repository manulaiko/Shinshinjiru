package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.oauth.OAuthServer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * Loading label presenter.
 * ========================
 *
 * Handles the rotation of the loading label.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
@Slf4j
public class LoadingLabel {
    @FXML
    private Label loading;

    @Autowired
    private OAuthServer server;

    @Value("${oauth.open-url}")
    private String openUrl;

    /**
     * Initializes the rotate transition in the loading label.
     */
    @FXML
    private void initialize() {
        var transition = new RotateTransition(Duration.seconds(1), loading);
        transition.setByAngle(360);
        transition.setDuration(Duration.seconds(2));
        transition.setDelay(Duration.ZERO);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();

        startOauthCallbackServer();
    }

    /**
     * Starts the OAuth callback server.
     *
     * @throws Exception If the server couldn't be started.
     */
    private void startOauthCallbackServer() {
        log.info("Starting OAuth Callback server...");
        server.start();

        log.info("Opening browser...");
        ShinshinjiruApplication.openInBrowser(openUrl);
    }
}
