package com.manulaiko.shinshinjiru.presenter;

import com.manulaiko.shinshinjiru.presenter.details.EntryInfo;
import com.manulaiko.shinshinjiru.presenter.details.ListEntry;
import com.manulaiko.shinshinjiru.presenter.lists.TableEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Setter;
import org.springframework.stereotype.Controller;

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
    private EntryInfo infoTabController;

    @FXML
    private ListEntry entryTabController;

    /**
     * Initializes the window content.
     */
    public void init() {
        var entry = this.entry.getEntry().getMedia();

        var titleStr = "";
        if (entry.getTitle() != null && entry.getTitle().getUserPreferred() != null) {
            titleStr = entry.getTitle().getUserPreferred();
        }

        var imageUrl = "";
        if (entry.getCoverImage() != null && entry.getCoverImage().getLarge() != null) {
            imageUrl = entry.getCoverImage().getLarge();
        }

        title.setText(titleStr);
        image.setImage(
                new Image(imageUrl)
        );

        infoTabController.init(entry);
        entryTabController.init(this.entry.getEntry());
    }
}
