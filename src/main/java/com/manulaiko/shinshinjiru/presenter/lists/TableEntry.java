package com.manulaiko.shinshinjiru.presenter.lists;

import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

/**
 * Table entry.
 * ============
 *
 * Represents a row from the list table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class TableEntry {
    private final SimpleStringProperty nameProperty;
    private final SimpleStringProperty progressProperty;
    private final SimpleStringProperty scoreProperty;
    private final MediaList entry;

    public TableEntry(MediaList entry) {
        this.nameProperty = new SimpleStringProperty(entry.getMedia().getTitle().getUserPreferred());
        this.progressProperty = new SimpleStringProperty(
                entry.getProgress() + "/" + entry.getMedia().getEpisodes()
        );
        this.scoreProperty = new SimpleStringProperty(entry.getScore() +" (avg: "+ entry.getMedia().getAverageScore() +")");

        this.entry = entry;
    }

    public String getName() {
        return this.nameProperty.get();
    }

    public String getProgress() {
        return this.progressProperty.get();
    }

    public String getScore() {
        return this.scoreProperty.get();
    }
}
