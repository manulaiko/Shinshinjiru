package com.manulaiko.shinshinjiru.presenter;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

@Controller
public class LoadingScreen {
    @FXML
    private Label loading;

    @FXML
    private void initialize() {
        var transition = new RotateTransition(Duration.seconds(1), loading);
        transition.setByAngle(360);
        transition.setDuration(Duration.seconds(2));
        transition.setDelay(Duration.ZERO);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();
    }
}
