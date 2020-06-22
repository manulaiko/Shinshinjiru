package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.MediaListEntryUpdated;
import com.manulaiko.shinshinjiru.api.event.UpdateMediaListEntryEvent;
import com.manulaiko.shinshinjiru.api.model.dto.FuzzyDateInput;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListResponseProjection;
import com.manulaiko.shinshinjiru.api.model.dto.SaveMediaListEntryMutationRequest;
import com.manulaiko.shinshinjiru.api.query.Updated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Update MediaList entry handler.
 * ===============================
 * <p>
 * Updates an entry in the user's list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class UpdateMediaListEntryHandler implements ApplicationListener<UpdateMediaListEntryEvent> {
    @Autowired
    private APIService api;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(UpdateMediaListEntryEvent event) {
        var entry = event.getEntry();

        log.debug("Updating "+ entry.getMedia().getTitle().getUserPreferred());
        var request = new SaveMediaListEntryMutationRequest.Builder()
                .setId(entry.getId())
                .setStatus(entry.getStatus())
                .setHiddenFromStatusLists(entry.getHiddenFromStatusLists())
                .setProgress(entry.getProgress())
                .setScore(entry.getScore())
                .setPriority(entry.getPriority())
                .setRepeat(entry.getRepeat())
                .setPrivate(entry.getPrivate())
                .setNotes(entry.getNotes());

        if (entry.getCompletedAt() != null) {
            request.setCompletedAt(
                    new FuzzyDateInput.Builder()
                            .setDay(entry.getCompletedAt().getDay())
                            .setMonth(entry.getCompletedAt().getMonth())
                            .setYear(entry.getCompletedAt().getYear())
                            .build()
            );
        }

        if (entry.getStartedAt() != null) {
            request.setStartedAt(
                    new FuzzyDateInput.Builder()
                            .setDay(entry.getStartedAt().getDay())
                            .setMonth(entry.getStartedAt().getMonth())
                            .setYear(entry.getStartedAt().getYear())
                            .build()
            );
        }

        var result = api.query(
                new GraphQLRequest(request.build(), new MediaListResponseProjection().id()),
                Updated.class
        );

        if (result.getData().get("SaveMediaListEntry") != null) {
            ShinshinjiruApplication.publish(new MediaListEntryUpdated(this, entry));
        }
        log.debug("Done.");
    }
}
