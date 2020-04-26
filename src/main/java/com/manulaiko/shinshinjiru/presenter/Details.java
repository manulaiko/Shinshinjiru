package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.model.dto.MediaTag;
import com.manulaiko.shinshinjiru.api.model.dto.Studio;
import com.manulaiko.shinshinjiru.api.model.dto.StudioEdge;
import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Details presenter.
 * ==================
 *
 * Presenter for the details window.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
public class Details {
    @Setter
    private TableEntry entry;

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

    public void init() {
        var entry = this.entry.getEntry().getMedia();

        title.setText(
                entry.getTitle().getUserPreferred()
        );
        image.setImage(
                new Image(entry.getCoverImage().getLarge())
        );
        synonyms.setText(
                String.join(System.lineSeparator(), entry.getSynonyms())
        );

        var tags   = entry.getTags().stream().map(MediaTag::getName).collect(Collectors.joining(", "));
        var genres = String.join(", ", entry.getGenres());

        this.tags.setText(
                genres + System.lineSeparator() + tags
        );

        description.setText(entry.getDescription().replaceAll("<br\\s*/?>", System.lineSeparator()));

        if (entry.getEpisodes() != null) {
            episodes.setText(entry.getEpisodes().toString());
        } else {
            episodes.setText("?");
        }

        var text = switch (entry.getStatus()) {
            case FINISHED -> "Finished";
            case RELEASING -> {
                if (entry.getNextAiringEpisode() != null) {
                    var episode = entry.getNextAiringEpisode().getEpisode();
                    var time    = entry.getNextAiringEpisode().getTimeUntilAiring();

                    yield "On going, episode " + episode + " airing in " + convertSeconds(time);
                }

                yield "On going";
            }
            case NOT_YET_RELEASED -> "Not released yet";
            case CANCELLED -> "Cancelled :(";
        };
        status.setText(text);

        studios.setText(
                entry.getStudios()
                     .getEdges()
                     .stream()
                     .map(StudioEdge::getNode)
                     .filter(Studio::getIsAnimationStudio)
                     .map(Studio::getName)
                     .collect(Collectors.joining(", "))
        );

        date.setText(
                Month.of(entry.getStartDate().getMonth()).getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                ", " +
                entry.getStartDate().getYear()
        );
    }

    /**
     * Converts seconds to display text.
     *
     * @param seconds Seconds to convert.
     *
     * @return Text to display.
     */
    private String convertSeconds(int seconds) {
        int  day    = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours  = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);

        return day + "d " + hours + "h " + minute + "m";
    }
}
