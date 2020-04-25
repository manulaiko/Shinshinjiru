package com.manulaiko.shinshinjiru.view.handler;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.AniList;
import com.manulaiko.shinshinjiru.presenter.lists.DetailEntry;
import com.manulaiko.shinshinjiru.view.SceneManager;
import com.manulaiko.shinshinjiru.view.event.ShowDetailsWindowEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Show Details window handler.
 * ============================
 *
 * Shows the details window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Slf4j
@Component
public class ShowDetailsWindowHandler implements ApplicationListener<ShowDetailsWindowEvent> {
    @Autowired
    private SceneManager sceneManager;

    @Autowired
    private DetailEntry detailEntry;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(ShowDetailsWindowEvent event) {
        var entry = event.getEntry();

        log.debug("Showing details window for "+ entry.getEntry().getMedia().getTitle().getUserPreferred());
        detailEntry.setEntry(entry);
        sceneManager.showNew("Details.fxml");
    }
}
