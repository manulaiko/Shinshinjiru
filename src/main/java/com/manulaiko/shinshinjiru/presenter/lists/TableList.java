package com.manulaiko.shinshinjiru.presenter.lists;

import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Table list.
 * ===========
 *
 * The presenter for the list TableView.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Slf4j
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
        super.setEditable(true);
        super.getSelectionModel().setCellSelectionEnabled(true);

        super.setOnKeyPressed(event -> {
            var pos = super.getFocusModel().getFocusedCell();
            if (pos != null && event.getCode().isLetterKey()) {
                super.edit(pos.getRow(), pos.getTableColumn());
            }
        });

        return this;
    }

    /**
     * Creates the columns.
     */
    private void createColumns() {
        //noinspection unchecked
        super.getColumns().addAll(
                createColumn("Name", .75, false, TableEntry::getNameProperty),
                createColumn("Progress", .124, true, TableEntry::getProgressProperty),
                createColumn("Score", .124, true, TableEntry::getScoreProperty)
        );
        super.getColumns().forEach(c -> c.setEditable(true));
    }

    /**
     * Creates and returns a table column.
     *
     * @param title    Column text.
     * @param size     Column total size in percentage relative to the table view.
     * @param property Column data property.
     *
     * @return Created cell;
     */
    private TableColumn<TableEntry, String> createColumn(
            String title, double size, boolean editable, Function<TableEntry, StringProperty> property
    ) {
        var col = new TableColumn<TableEntry, String>(title);

        col.prefWidthProperty().bind(super.widthProperty().multiply(size));
        col.setCellValueFactory(c -> property.apply(c.getValue()));
        if (editable) {
            col.setCellFactory(c -> EditCell.createStringEditCell());
        }

        return col;
    }
}
