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
    private SimpleStringProperty name;
    private SimpleStringProperty progress;
    private SimpleStringProperty score;

    public TableEntry(MediaList entry) {
        name = new SimpleStringProperty(entry.getMedia().getTitle().getUserPreferred());
        progress = new SimpleStringProperty(
                entry.getProgress() + "/" + entry.getMedia().getEpisodes()
        );
        score = new SimpleStringProperty(entry.getScore() +" (avg: "+ entry.getMedia().getAverageScore() +")");
    }

    public String getName() {
        return name.get();
    }

    public String getProgress() {
        return progress.get();
    }

    public String getScore() {
        return score.get();
    }
}
