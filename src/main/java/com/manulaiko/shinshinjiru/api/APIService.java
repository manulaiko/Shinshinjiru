package com.manulaiko.shinshinjiru.api;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.*;
import com.manulaiko.shinshinjiru.api.model.dto.MediaListCollection;
import com.manulaiko.shinshinjiru.api.model.dto.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * API Service.
 * ============
 * <p>
 * Contains the API implementation.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
@Data
@Slf4j
public class APIService {
    private APIToken token;

    @Value("${api.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * The user instance.
     */
    private User user;

    /**
     * The user lists.
     */
    private MediaListCollection lists;

    /**
     * Executes a query against the API.
     *
     * @param query Query to execute.
     * @return Query result.
     */
    public <T extends GraphQLResult<?>> T query(GraphQLRequest query, Class<T> type) {
        return query(query.toString(), type);
    }

    /**
     * Executes a raw query.
     *
     * @param query Query to execute.
     *
     * @return Query result.
     */
    public <T extends GraphQLResult<?>> T query(String query, Class<T> type) {
        var headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentLength(query.length());
        headers.setContentType(MediaType.APPLICATION_JSON);

        var entity = new HttpEntity<>(query, headers);

        var result = restTemplate.exchange(
                URI.create(url),
                HttpMethod.POST,
                entity,
                type
        ).getBody();

        if (result != null && result.hasErrors()) {
            throw new RuntimeException(result.getErrors().get(0).getMessage());
        }

        return result;
    }

    /**
     * Sets the User instance when the user is initialized.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userInitializedHandler(UserInitializedEvent event) {
        log.info("User initialized.");
        user = event.getUser();

        ShinshinjiruApplication.publish(new PostUserInitializedEvent(this, user));
    }

    /**
     * Sets the User lists when they are initialized.
     *
     * @param event Fired event.
     */
    @EventListener
    public void userListsInitializedHandler(UserListsInitializedEvent event) {
        log.debug("User lists initialized!");
        lists = event.getLists();

        ShinshinjiruApplication.publish(new PostUserListsInitializedEvent(this, lists));
    }
}
