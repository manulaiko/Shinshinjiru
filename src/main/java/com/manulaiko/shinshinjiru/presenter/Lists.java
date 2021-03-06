package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.MediaListEntryDeletedEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import com.manulaiko.shinshinjiru.presenter.lists.TableList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * Lists presenter.
 * ================
 * <p>
 * Controller for the List.fxml view.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
@Slf4j
public class Lists {
    @FXML
    private TabPane mediaLists;

    @Autowired
    private APIService api;

    /**
     * Initializes the lists.
     */
    @FXML
    public void initialize() {
        log.debug("Loading List entries...");

        this.updateLists(api.getLists());
    }

    /**
     * Update list tabs when the lists are initialized.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userListsInitializedHandler(UserListsInitializedEvent event) {
        if (mediaLists == null) {
            // not initialized yet.
            return;
        }

        Platform.runLater(() -> this.updateLists(event.getLists()));
    }

    /**
     * Updates the list tabs.
     *
     * @param lists List content.
     */
    private void updateLists(MediaListCollection lists) {
        var tabs = mediaLists.getTabs();
        tabs.clear();

        lists.getLists()
                .stream()
                .map(this::loadList)
                .forEach(tabs::add);
    }

    /**
     * Loads the list onto the view.
     *
     * @param list List to load.
     */
    private Tab loadList(MediaListGroup list) {
        log.debug("Building table for " + list.getName());
        var tab = new Tab();

        tab.setText(list.getName());
        tab.setContent(new TableList().initialize(list));

        return tab;
    }

    /**
     * Deletes an entry from the list.
     *
     * @param event Fired event.
     */
    @EventListener
    public void deleteEntry(MediaListEntryDeletedEvent event) {
        mediaLists.getTabs()
                .stream()
                .map(t -> (TableList) t.getContent())
                .forEach(t -> t.deleteEntry(event));
    }
}
