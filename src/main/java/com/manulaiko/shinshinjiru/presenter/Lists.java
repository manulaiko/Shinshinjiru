package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.MediaListEntryDeletedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import com.manulaiko.shinshinjiru.presenter.lists.TableList;
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
        var tabs = mediaLists.getTabs();

        api.getLists()
                .getLists()
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
