package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowLoadingLabelEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Show loading label handler.
 * ===========================
 *
 * Shows the loading label.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class ShowLoadingLabelHandler implements ApplicationListener<ShowLoadingLabelEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(ShowLoadingLabelEvent event) {
        log.info("Loading...");
        sceneManager.show("LoadingLabel.fxml");
    }
}
