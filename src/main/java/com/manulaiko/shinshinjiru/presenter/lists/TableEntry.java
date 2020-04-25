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
    private final SimpleStringProperty name;
    private final SimpleStringProperty progress;
    private final SimpleStringProperty score;
    private final SimpleStringProperty notes;
    private final MediaList entry;

    /**
     * Constructor.
     *
     * @param entry Media entry.
     */
    public TableEntry(MediaList entry) {
        this.name = new SimpleStringProperty(entry.getMedia().getTitle().getUserPreferred());
        this.progress = new SimpleStringProperty(
                entry.getProgress() + "/" + (entry.getMedia().getEpisodes() == null ? "?" : entry.getMedia().getEpisodes())
        );
        this.score = new SimpleStringProperty(entry.getScore() +" (avg: "+ entry.getMedia().getAverageScore() +")");
        this.notes = new SimpleStringProperty(entry.getNotes());

        this.entry = entry;
    }
}
