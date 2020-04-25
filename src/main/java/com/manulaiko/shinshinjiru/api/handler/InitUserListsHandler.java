package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.AniList;
import com.manulaiko.shinshinjiru.api.event.InitUserListsEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
import com.manulaiko.shinshinjiru.api.model.dto.*;
import com.manulaiko.shinshinjiru.api.query.UserLists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Init user list handler.
 * =======================
 *
 * Initializes the user's list.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class InitUserListsHandler implements ApplicationListener<InitUserListsEvent> {
    @Autowired
    private AniList anilist;

    @Autowired
    private APIService api;

    /**
     * @inheritDoc
     */
    @Override
    public void onApplicationEvent(InitUserListsEvent event) {
        log.info("Loading user lists...");
        log.debug("User: " + anilist.getUser().getName());

        var request = new MediaListCollectionQueryRequest();
        request.setUserId(anilist.getUser().getId());
        request.setType(MediaType.ANIME);

        var result = api.query(
                new GraphQLRequest(
                        request,
                        // rip
                        new MediaListCollectionResponseProjection().lists(
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
                                                                        .startDate(
                                                                                new FuzzyDateResponseProjection().year()
                                                                                                                 .month()
                                                                        )
                                                                        .synonyms()
                                                                        .genres()
                                                                        .tags(
                                                                                new MediaTagResponseProjection().name()
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
                        )
                ),
                UserLists.class
        );

        ShinshinjiruApplication.publish(new UserListsInitializedEvent(this, result.getData().get("MediaListCollection")));
    }
}
