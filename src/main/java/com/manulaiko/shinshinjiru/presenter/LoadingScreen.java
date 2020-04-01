package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.oauth.OAuthServer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Loading screen presenter.
 * =========================
 *
 * Handles the loading screen logic.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
public class LoadingScreen {
    private static final Logger log = LoggerFactory.getLogger(LoadingScreen.class);

    @FXML
    private Label loading;

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

        var server = new OAuthServer();
        server.start();

        log.info("Opening browser...");
        ShinshinjiruApplication.openInBrowser(server.getUrl());
    }
}
