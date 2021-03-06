package com.manulaiko.shinshinjiru.view.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.APIToken;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.oauth.event.StartOauthServerEvent;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.InitMainScreenEvent;
import com.manulaiko.shinshinjiru.view.event.ShowLoadingLabelEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.FileBasedConfiguration;
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

    @Autowired
    private FileBasedConfiguration config;

    @Autowired
    private APIService api;

    /**
     * @inheritDoc
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(InitMainScreenEvent event) {
        log.info("Loading MainScreen...");
        sceneManager.show();

        // Show loading
        ShinshinjiruApplication.publish(new ShowLoadingLabelEvent(this));

        var token = config.getString("api.token", "");
        if (token.isEmpty()) {
            log.info("API Token not found in configuration file. Start OAuth...");
            // Start OAuth server
            ShinshinjiruApplication.publish(new StartOauthServerEvent(this));

            return;
        }

        log.info("API Token found in configuration file.");

        api.setToken(new ObjectMapper().readValue(token, APIToken.class));
        ShinshinjiruApplication.publish(new InitUserEvent(this));
    }
}
