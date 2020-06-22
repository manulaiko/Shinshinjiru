package com.manulaiko.shinshinjiru.api.handler;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.api.APIService;
import com.manulaiko.shinshinjiru.api.event.ReplyActivityEvent;
import com.manulaiko.shinshinjiru.api.model.dto.ActivityReplyResponseProjection;
import com.manulaiko.shinshinjiru.api.model.dto.SaveActivityReplyMutationRequest;
import com.manulaiko.shinshinjiru.api.query.ActivityReplyQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Reply activity handler.
 * =======================
 * <p>
 * Replies to an activity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Component
@Slf4j
public class ReplyActivityHandler implements ApplicationListener<ReplyActivityEvent> {
    @Autowired
    private APIService api;

    @Override
    public void onApplicationEvent(ReplyActivityEvent event) {
        var request = new SaveActivityReplyMutationRequest.Builder()
                .setActivityId(event.getActivity().getId())
                .setText(event.getText());

        var result = api.query(new GraphQLRequest(
                        request.build(),
                        new ActivityReplyResponseProjection().id()
                ),
                ActivityReplyQuery.class
        );

        if (result.getData().get("SaveActivityReply") != null) {
            log.info("Activity reply saved!");
        }
    }
}
