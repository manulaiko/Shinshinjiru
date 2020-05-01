package com.manulaiko.shinshinjiru.api;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;
import com.manulaiko.shinshinjiru.ShinshinjiruApplication;
import com.manulaiko.shinshinjiru.api.event.InitUserEvent;
import com.manulaiko.shinshinjiru.api.event.InitUserListsEvent;
import com.manulaiko.shinshinjiru.api.event.UserInitializedEvent;
import com.manulaiko.shinshinjiru.api.event.UserListsInitializedEvent;
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

    private RestTemplate restTemplate = new RestTemplate();

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
        var request = query.toString();
        var headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentLength(request.length());
        headers.setContentType(MediaType.APPLICATION_JSON);

        var entity = new HttpEntity<>(request, headers);

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
     * Returns the user.
     *
     * @return The authenticated user.
     */
    public User getUser() {
        if (user == null) {
            ShinshinjiruApplication.publish(new InitUserEvent(this));
        }

        return user;
    }

    /**
     * Returns the lists.
     *
     * @return Authenticated user's lists.
     */
    public MediaListCollection getLists() {
        log.info("Retrieving lists");
        if (lists == null) {
            ShinshinjiruApplication.publish(new InitUserListsEvent(this));
        }

        log.info("Lists loaded!");
        return lists;
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
    }
}
