package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.oauth.event.StartOauthServerEvent;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.InitMainScreenEvent;
import com.manulaiko.shinshinjiru.view.event.ShowLoadingLabelEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Init main screen handler.
 * =========================
 *
 * Initializes the main screen.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class InitMainScreenHandler implements ApplicationListener<InitMainScreenEvent> {
    @Autowired
    private SceneManager sceneManager;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitMainScreenEvent event) {
        log.info("Loading MainScreen...");
        sceneManager.show();

        // Show loading
        ShinshinjiruApplication.publish(new ShowLoadingLabelEvent(this));

        // Start OAuth server
        ShinshinjiruApplication.publish(new StartOauthServerEvent(this));
    }
}
