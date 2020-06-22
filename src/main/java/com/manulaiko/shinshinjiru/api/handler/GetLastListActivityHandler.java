package com.manulaiko.shinshinjiru.api.handler;

import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.GetLastListActivity;
import com.manulaiko.shinshinjiru.api.query.ListActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Get last ListActivity handler.
 * ==============================
 * <p>
 * Loads the last ListActivity from the specified media.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
public class GetLastListActivityHandler implements ApplicationListener<GetLastListActivity> {
    @Autowired
    private APIService api;

    @Override
    public void onApplicationEvent(GetLastListActivity event) {
        var query = ("{\"query\":\"query { Activity (userId: $userId, mediaId: $mediaId, type: MEDIA_LIST, sort: ID_DESC) { ... on ListActivity { id, status } } }\"}")
                .replaceAll("\\$userId", String.valueOf(api.getUser().getId()))
                .replaceAll("\\$mediaId", String.valueOf(event.getEntry().getMedia().getId()));

        var result = api.query(query, ListActivity.class);

        event.getCallback().accept(result.getData().get("Activity"));
    }
}
