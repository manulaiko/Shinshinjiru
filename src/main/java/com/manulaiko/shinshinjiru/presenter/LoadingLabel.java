package com.manulaiko.shinshinjiru.presenter;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
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
        transition.setCycleCount(Animation.INDEFINITE);
        transition.play();
    }
}
