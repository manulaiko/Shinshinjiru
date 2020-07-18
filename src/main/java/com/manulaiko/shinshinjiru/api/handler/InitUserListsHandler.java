package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.InitUserListsEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.*;
import com.manulaiko.shinshinjiru.api.query.UserLists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Init user list handler.
 * =======================
 * <p>
 * Initializes the user's list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class InitUserListsHandler implements ApplicationListener<InitUserListsEvent> {
    @Autowired
    private APIService api;

    @Autowired
    private FileBasedConfiguration config;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitUserListsEvent event) {
        log.info("Loading user lists...");
        log.debug("User: " + api.getUser().getName());

        var request = new MediaListCollectionQueryRequest.Builder()
                .setUserId(api.getUser().getId())
                .setType(MediaType.ANIME);

        var result = api.query(
                new GraphQLRequest(
                        request.build(),
                        getResponseProjection()
                ),
                UserLists.class
        );

        config.setProperty("app.lastSync", ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

        ShinshinjiruApplication.publish(new UserListsInitializedEvent(
                this,
                result.getData().get("MediaListCollection")
        ));
    }

    /**
     * Returns the response projection instance.
     *
     * @return Response projection.
     */
    private MediaListCollectionResponseProjection getResponseProjection() {
        var date = new FuzzyDateResponseProjection()
                .month()
                .year()
                .day();

        return new MediaListCollectionResponseProjection().lists(
                new MediaListGroupResponseProjection()
                        .name()
                        .entries(
                                new MediaListResponseProjection()
                                        .id()
                                        .status()
                                        .score()
                                        .progress()
                                        .repeat()
                                        .priority()
                                        .notes()
                                        .hiddenFromStatusLists()
                                        .customLists()
                                        .notes()
                                        .startedAt(date)
                                        .completedAt(date)
                                        .media(
                                                new MediaResponseProjection()
                                                        .studios(
                                                                new StudioConnectionResponseProjection()
                                                                        .edges(
                                                                                new StudioEdgeResponseProjection()
                                                                                        .isMain()
                                                                                        .node(
                                                                                                new StudioResponseProjection()
                                                                                                        .name()
                                                                                                        .isAnimationStudio()
                                                                                        )
                                                                        )
                                                        )
                                                        .id()
                                                        .title(
                                                                new MediaTitleResponseProjection()
                                                                        .userPreferred()
                                                        )
                                                        .averageScore()
                                                        .description()
                                                        .format()
                                                        .status()
                                                        .episodes()
                                                        .duration()
                                                        .coverImage(new MediaCoverImageResponseProjection()
                                                                .large()
                                                        )
                                                        .season()
                                                        .startDate(date)
                                                        .synonyms()
                                                        .genres()
                                                        .tags(
                                                                new MediaTagResponseProjection()
                                                                        .name()
                                                                        .rank()
                                                                        .category()
                                                        )
                                                        .nextAiringEpisode(
                                                                new AiringScheduleResponseProjection()
                                                                        .airingAt()
                                                                        .episode()
                                                                        .timeUntilAiring()
                                                        )
                                                        .relations(
                                                                new MediaConnectionResponseProjection()
                                                                        .edges(
                                                                                new MediaEdgeResponseProjection()
                                                                                        .relationType()
                                                                                        .node(
                                                                                                new MediaResponseProjection()
                                                                                                        .id()
                                                                                        )
                                                                        )
                                                        )
                                        )
                        )
        );
    }
}
