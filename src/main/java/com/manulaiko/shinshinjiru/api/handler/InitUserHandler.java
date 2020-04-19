package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.api.model.dto.*;
import com.manulaiko.shinshinjiru.api.query.Viewer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Init user handler.
 * ==================
 *
 * Initializes the authenticated user.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class InitUserHandler implements ApplicationListener<InitUserEvent> {
    @Autowired
    private APIService api;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitUserEvent event) {
        log.debug("Loading user...");

        var request = new GraphQLRequest(
                new ViewerQueryRequest(),
                new UserResponseProjection()
                        .id()
                        .name()
                        .avatar(new UserAvatarResponseProjection().medium())
                        .mediaListOptions(
                                new MediaListOptionsResponseProjection()
                                        .animeList(
                                                new MediaListTypeOptionsResponseProjection().customLists()
                                        )
                                        .scoreFormat()
                        )
        );

        var result = api.query(request, Viewer.class);

        log.info("ViewerQueryRequest result " + result);
    }
}
