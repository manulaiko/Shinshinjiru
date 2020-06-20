package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.InitUserListsEvent;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowListViewEvent;
import com.manulaiko.shinshinjiru.view.event.ShowLoadingLabelEvent;
import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Show List view handler.
 * =======================
 *
 * Shows the List.fxml view.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Slf4j
@Component
public class ShowListViewHandler implements ApplicationListener<ShowListViewEvent> {
    @Autowired
    private APIService api;

    @Autowired
    private SceneManager sceneManager;

    @Override
    public void onApplicationEvent(ShowListViewEvent event) {
        if (api.getLists() == null) {
            log.debug("User lists not initialized.");
            ShinshinjiruApplication.publish(new ShowLoadingLabelEvent(this));
            ShinshinjiruApplication.publish(new InitUserListsEvent(this));

            return;
        }

        Platform.runLater(() -> sceneManager.show("List.fxml"));
    }
}
