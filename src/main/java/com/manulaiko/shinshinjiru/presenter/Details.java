package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.model.dto.MediaTag;
import com.manulaiko.shinshinjiru.api.model.dto.Studio;
import com.manulaiko.shinshinjiru.api.model.dto.StudioEdge;
import com.manulaiko.shinshinjiru.presenter.lists.DetailEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Details presenter.
 * ==================
 *
 * Presenter for the details window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class Details {
    @Autowired
    private DetailEntry entry;

    @FXML
    private Label title;

    @FXML
    private ImageView image;

    @FXML
    private TextArea synonyms;

    @FXML
    private TextArea tags;

    @FXML
    private TextArea description;

    @FXML
    private Label episodes;

    @FXML
    private Label status;

    @FXML
    private Label studios;

    @FXML
    private Label date;

    @FXML
    public void initialize() {
        var entry = this.entry.getEntry().getEntry().getMedia();

        title.setText(
                entry.getTitle().getUserPreferred()
        );
        image.setImage(
                new Image(entry.getCoverImage().getLarge())
        );
        synonyms.setText(
                String.join(System.lineSeparator(), entry.getSynonyms())
        );

        var tags = entry.getTags().stream().map(MediaTag::getName).collect(Collectors.joining(", "));
        var genres = String.join(", ", entry.getGenres());

        this.tags.setText(
                genres + System.lineSeparator() + tags
        );

        description.setText(entry.getDescription().replaceAll("<br\\s*/?>", System.lineSeparator()));

        episodes.setText(entry.getEpisodes().toString());

        switch(entry.getStatus()) {
            case FINISHED:
                status.setText("Finished");
                break;

            case RELEASING:
                status.setText("On going");
                break;

            case NOT_YET_RELEASED:
                status.setText("Not released yet");
                break;

            case CANCELLED:
                status.setText("Cancelled :(");
                break;
        }

        studios.setText(
                entry.getStudios()
                     .getEdges()
                     .stream()
                     .map(StudioEdge::getNode)
                     .filter(Studio::getIsAnimationStudio)
                     .map(Studio::getName)
                     .collect(Collectors.joining(", "))
        );

        date.setText(entry.getStartDate().getMonth() + "/" + entry.getStartDate().getYear());
    }
}
