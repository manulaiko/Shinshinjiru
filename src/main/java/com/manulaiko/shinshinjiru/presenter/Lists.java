package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.AniList;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import com.manulaiko.shinshinjiru.presenter.lists.TableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Lists presenter.
 * ================
 *
 * Controller for the List.fxml view.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
@Slf4j
public class Lists {
    @FXML
    private TabPane lists;

    @Autowired
    private AniList anilist;

    /**
     * Initializes the lists.
     */
    @FXML
    public void initialize() {
        log.debug("Loading List entries...");
        var tabs = lists.getTabs();

        anilist.getLists()
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
}
