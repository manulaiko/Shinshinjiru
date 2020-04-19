package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.AniList;
import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

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

        var lists = anilist.getLists();

        lists.getLists()
             .forEach(
                     l -> Platform.runLater(
                             () -> loadList(l)
                     )
             );
    }

    /**
     * Loads the list onto the view.
     *
     * @param list List to load.
     */
    private void loadList(MediaListGroup list) {
        var tab = new Tab();
        tab.setText(list.getName());

        tab.setContent(this.buildList(list));

        lists.getTabs().add(tab);
    }

    /**
     * Builds the list table.
     *
     * @param entries Entries to populate the table.
     *
     * @return List table.
     */
    private Node buildList(MediaListGroup entries) {
        log.debug("Building table for " + entries.getName());

        var table = new TableView<TableEntry>();
        var data = FXCollections.observableList(
                entries.getEntries()
                       .stream()
                       .map(TableEntry::new)
                       .collect(Collectors.toList())
        );

        // Build columns
        var name = new TableColumn<TableEntry, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        var progress = new TableColumn<TableEntry, String>("Progress");
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        var score = new TableColumn<TableEntry, String>("Score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(name, progress, score);
        table.setItems(data);

        return table;
    }
}
