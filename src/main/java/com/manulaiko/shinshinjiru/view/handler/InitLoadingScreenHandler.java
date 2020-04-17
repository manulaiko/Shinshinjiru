package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.InitLoadingScreenEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Init loading screen handler.
 * ============================
 *
 * Initializes the loading screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class InitLoadingScreenHandler implements ApplicationListener<InitLoadingScreenEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitLoadingScreenEvent event) {
        log.info("Loading LoadingScreen...");
        sceneManager.show("LoadingScreen.fxml");
    }
}
