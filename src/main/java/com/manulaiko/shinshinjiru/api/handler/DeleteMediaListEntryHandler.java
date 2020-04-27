package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.DeleteMediaListEntryEvent;
import com.manulaiko.shinshinjiru.api.event.MediaListEntryDeletedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.DeleteMediaListEntryMutationRequest;
import com.manulaiko.shinshinjiru.api.model.dto.DeletedResponseProjection;
import com.manulaiko.shinshinjiru.api.query.Deleted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Delete MediaList entry handler.
 * ===============================
 *
 * Deletes an entry from the user's media list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class DeleteMediaListEntryHandler implements ApplicationListener<DeleteMediaListEntryEvent> {
    @Autowired
    private APIService api;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(DeleteMediaListEntryEvent event) {
        log.debug("Deleting " + event.getEntry().getMedia().getTitle().getUserPreferred() + "...");

        var request = new DeleteMediaListEntryMutationRequest();
        request.setId(event.getEntry().getId());

        var result = api.query(
                new GraphQLRequest(
                        request,
                        new DeletedResponseProjection().deleted()
                ),
                Deleted.class
        );

        if (result.getData().get("DeleteMediaListEntry").getDeleted()) {
            log.debug("Deleted!");

            ShinshinjiruApplication.publish(new MediaListEntryDeletedEvent(this, event.getEntry()));
        }
    }
}
