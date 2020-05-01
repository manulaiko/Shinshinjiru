package com.manulaiko.shinshinjiru.presenter.details;

import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.*;
import com.manulaiko.shinshinjiru.api.model.dto.FuzzyDate;
import com.manulaiko.shinshinjiru.api.model.dto.MediaList;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListStatus;
import com.manulaiko.shinshinjiru.view.event.ConfirmAlertEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

/**
 * List entry presenter.
 * =====================
 *
 * Presenter for the list entry tab.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
@Slf4j
public class ListEntry {
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

    @FXML
    private TextArea activityReply;

    @FXML
    private DatePicker entryStartDate;

    @FXML
    private DatePicker entryEndDate;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Label entryProgressTotal;

    @FXML
    private Label entryScoreAvg;

    private MediaList entry;

    /**
     * Initializes the List Entry tab.
     *
     * @param entry Entry to initialize.
     */
    public void init(MediaList entry) {
        this.entry = entry;

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
     * Handles the list entry update button click.
     *
     * @param event Fired event.
     */
    private void update(MouseEvent event) {
        entry.setStatus(switch (entryStatus.getValue()) {
            case "Completed" -> MediaListStatus.COMPLETED;
            case "Watching" -> MediaListStatus.CURRENT;
            case "Paused" -> MediaListStatus.PAUSED;
            case "Dropped" -> MediaListStatus.DROPPED;
            case "Plan to Watch" -> MediaListStatus.PLANNING;
            case "Repeating" -> MediaListStatus.REPEATING;
            default -> throw new IllegalStateException("Unexpected value: " + entryStatus.getValue());
        });

        entry.setHiddenFromStatusLists(entryHide.isSelected());
        entry.setProgress(entryProgress.getValue());
        entry.setScore(entryScore.getValue());
        entry.setRepeat(entryRepeat.getValue());
        entry.setPriority(entryPriority.getValue());
        entry.setNotes(entryNotes.getText());
        entry.setStartedAt(getDate(entryStartDate));
        entry.setCompletedAt(getDate(entryEndDate));

        ShinshinjiruApplication.publish(new UpdateMediaListEntryEvent(this, entry));
    }

    /**
     * Adds the activity reply.
     *
     * @param event Fired event.
     */
    @EventListener
    public void replyActivity(MediaListEntryUpdated event) {
        ShinshinjiruApplication.publish(new ConfirmAlertEvent(this, "Entry successfully updated!"));

        if (!this.activityReply.getText().isBlank()) {
            ShinshinjiruApplication.publish(new GetLastListActivity(
                    this,
                    event.getEntry(),
                    a -> ShinshinjiruApplication.publish(new ReplyActivityEvent(this, a, activityReply.getText()))
            ));
        }
    }

    /**
     * Handles the list entry delete button click.
     *
     * @param event Fired event.
     */
    private void delete(MouseEvent event) {
        ShinshinjiruApplication.publish(new DeleteMediaListEntryEvent(this, entry));
    }

    /**
     * Sets the date of a field
     *
     * @param field Field to set.
     * @param date  Date to set.
     */
    private void setDate(DatePicker field, FuzzyDate date) {
        if (date == null || date.getYear() == null || date.getMonth() == null || date.getDay() == null) {
            return;
        }

        field.setValue(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
    }

    /**
     * Returns a date from a field.
     *
     * @param field Field to get.
     * @return Field's date.
     */
    private FuzzyDate getDate(DatePicker field) {
        try {
            var date = field.getValue();

            return new FuzzyDate.Builder()
                    .setDay(date.getDayOfMonth())
                    .setMonth(date.getMonthValue())
                    .setYear(date.getYear())
                    .build();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
