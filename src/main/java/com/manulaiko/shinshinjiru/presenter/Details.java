package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.api.model.dto.FuzzyDate;
import com.manulaiko.shinshinjiru.api.model.dto.MediaTag;
import com.manulaiko.shinshinjiru.api.model.dto.Studio;
import com.manulaiko.shinshinjiru.api.model.dto.StudioEdge;
import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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

    @FXML
    private ChoiceBox<String> entryStatus;

    @FXML
    private CheckBox entryHide;

    @FXML
    private Spinner<Integer> entryProgress;

    @FXML
    private Spinner<Double> entryScore;

    @FXML
    private Spinner<Integer> entryRepeat;

    @FXML
    private Spinner<Integer> entryPriority;

    @FXML
    private TextArea entryNotes;

    //@FXML
    //private TextArea activityReply;

    @FXML
    private DatePicker entryStartDate;

    @FXML
    private DatePicker entryEndDate;

    @FXML
    private Button update;

    @FXML
    Button delete;

    @FXML
    private Label entryProgressTotal;

    @FXML
    private Label entryScoreAvg;

    /**
     * Initializes the window content.
     */
    public void init() {
        initInfo();
        initListEntry();
    }

    /**
     * Initializes the List Entry tab.
     */
    private void initListEntry() {
        var entry = this.entry.getEntry();

        entryStatus.getItems().addAll(
                "Completed",
                "Watching",
                "Paused",
                "Dropped",
                "Plan to Watch",
                "Repeating"
        );
        entryStatus.setValue(switch (entry.getStatus()) {
            case COMPLETED -> "Completed";
            case CURRENT -> "Watching";
            case PAUSED -> "Paused";
            case DROPPED -> "Dropped";
            case PLANNING -> "Plan to Watch";
            case REPEATING -> "Repeating";
        });

        entryHide.setSelected((entry.getHiddenFromStatusLists() != null) ? entry.getHiddenFromStatusLists() : false);

        entryProgress.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                entry.getMedia().getEpisodes() != null ? entry.getMedia().getEpisodes() : Integer.MAX_VALUE,
                entry.getProgress() != null ? entry.getProgress() : 0
        ));

        entryScore.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(
                0,
                100,
                entry.getScore() != null ? entry.getScore() : 0
        ));

        entryRepeat.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                Integer.MAX_VALUE,
                entry.getRepeat() != null ? entry.getRepeat() : 0
        ));
        entryPriority.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                Integer.MAX_VALUE,
                entry.getPriority() != null ? entry.getPriority() : 0
        ));

        entryProgress.getValueFactory().setValue((entry.getProgress() != null) ? entry.getProgress() : 0);
        entryScore.getValueFactory().setValue((entry.getScore() != null) ? entry.getScore() : 0);
        entryRepeat.getValueFactory().setValue((entry.getRepeat() != null) ? entry.getRepeat() : 0);
        entryPriority.getValueFactory().setValue((entry.getPriority() != null) ? entry.getPriority() : 0);

        entryNotes.setText((entry.getNotes() != null) ? entry.getNotes() : "");

        entryProgressTotal.setText("/ " + (
                (entry.getMedia().getEpisodes() != null) ?
                entry.getMedia().getEpisodes() :
                "?"
        ));
        entryScoreAvg.setText("avg " + (
                (entry.getMedia().getAverageScore() != null) ?
                entry.getMedia().getAverageScore() :
                "?"
        ));

        setDate(entryStartDate, entry.getStartedAt());
        setDate(entryEndDate, entry.getCompletedAt());

        update.setOnMouseClicked(this::update);
        delete.setOnMouseClicked(this::delete);
    }

    /**
     * Initializes the Information tab
     */
    private void initInfo() {
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
     * Handles the list entry update button click.
     *
     * @param event Fired event.
     */
    private void update(MouseEvent event) {

    }

    /**
     * Handles the list entry delete button click.
     *
     * @param event Fired event.
     */
    private void delete(MouseEvent event) {

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

    /**
     * Sets the date of a field
     *
     * @param field Field to set.
     * @param date Date to set.
     */
    private void setDate(DatePicker field, FuzzyDate date) {
        if (date == null || date.getYear() == null || date.getMonth() == null || date.getDay() == null) {
            return;
        }

        field.setValue(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
    }
}
