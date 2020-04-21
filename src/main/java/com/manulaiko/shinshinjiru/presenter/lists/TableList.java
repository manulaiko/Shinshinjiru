package com.manulaiko.shinshinjiru.presenter.lists;

import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

/**
 * Table list.
 * ===========
 *
 * The presenter for the list TableView.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class TableList extends TableView<TableEntry> {
    /**
     * Parses the entries and creates the columns.
     *
     * @param entries Table entries.
     *
     * @return This instance
     */
    public TableList initialize(MediaListGroup entries) {
        this.createColumns();

        super.setItems(
                entries.getEntries()
                       .parallelStream()
                       .map(TableEntry::new)
                       .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
        super.setPrefSize(960, 450);

        return this;
    }

    /**
     * Creates the columns.
     */
    private void createColumns() {
        // Build columns
        var name = new TableColumn<TableEntry, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        var progress = new TableColumn<TableEntry, String>("Progress");
        progress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        var score = new TableColumn<TableEntry, String>("Score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        name.prefWidthProperty().bind(super.widthProperty().multiply(.75));
        progress.prefWidthProperty().bind(super.widthProperty().multiply(.124));
        score.prefWidthProperty().bind(super.widthProperty().multiply(.124));

        //noinspection unchecked
        super.getColumns().addAll(name, progress, score);
    }
}
