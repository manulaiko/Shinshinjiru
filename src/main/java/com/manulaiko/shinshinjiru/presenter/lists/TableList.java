package com.manulaiko.shinshinjiru.presenter.lists;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.MediaListEntryDeletedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import com.manulaiko.shinshinjiru.view.event.ConfirmAlertEvent;
import com.manulaiko.shinshinjiru.view.event.ShowDetailsWindowEvent;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
@SuppressWarnings({"unchecked", "java:S110"})
@Slf4j
public class TableList extends TableView<TableEntry> {
    private String name;

    /**
     * Parses the entries and creates the columns.
     *
     * @param entries Table entries.
     *
     * @return This instance
     */
    public TableList initialize(MediaListGroup entries) {
        name = entries.getName();

        createOnClick();
        createColumns();

        setItems(
                entries.getEntries()
                       .parallelStream()
                       .map(TableEntry::new)
                       .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
        setPrefSize(960, 450);

        return this;
    }

    /**
     * Creates the row click event listener.
     */
    private void createOnClick() {
        setRowFactory(tv -> {
            var row = new TableRow<TableEntry>();
            row.setOnMouseClicked(this::onClick);

            return row;
        });
    }

    /**
     * Creates the columns.
     */
    private void createColumns() {
        getColumns().addAll(
                createColumn("Name", .5, TableEntry::getName),
                createColumn("Progress", .125, TableEntry::getProgress),
                createColumn("Score", .125, TableEntry::getScore),
                createColumn("Notes", .25, TableEntry::getNotes)
        );
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
            String title, double size, Function<TableEntry, StringProperty> property
    ) {
        var col = new TableColumn<TableEntry, String>(title);

        col.prefWidthProperty()
           .bind(widthProperty().multiply(size).subtract(5));
        col.setCellValueFactory(c -> property.apply(c.getValue()));

        return col;
    }

    /**
     * Handles row onClick events.
     *
     * @param event Fired event.
     */
    private void onClick(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() != 2) {
            ShinshinjiruApplication.publish(new ShowDetailsWindowEvent(
                    this,
                    getSelectionModel().getSelectedItem()
            ));
        }
    }

    /**
     * Deletes an entry from the list.
     *
     * @param event Fired event.
     */
    public void deleteEntry(MediaListEntryDeletedEvent event) {
        var deleted = getItems().removeIf(
                e -> e.getEntry()
                      .getId()
                      .equals(event.getEntry().getId())
        );

        if (deleted) {
            var title = event.getEntry()
                             .getMedia()
                             .getTitle()
                             .getUserPreferred();

            ShinshinjiruApplication.publish(new ConfirmAlertEvent(this, title + " deleted from " + name));
        }
    }
}
