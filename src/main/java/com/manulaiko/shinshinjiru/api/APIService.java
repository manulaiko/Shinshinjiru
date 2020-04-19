package com.manulaiko.shinshinjiru.api;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * API Service.
 * ============
 *
 * Contains the API implementation.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
@Data
public class APIService {
    private APIToken token;

    @Value("${api.url}")
    private String url;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Executes a query against the API.
     *
     * @param query Query to execute.
     *
     * @return Query result.
     */
    public <T> Map<String, T> query(GraphQLRequest query, Class<T> type) {
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
                new ParameterizedTypeReference<GraphQLResult<Map<String, T>>>() {
                }
        ).getBody();

        if (result.hasErrors()) {
            throw new RuntimeException(result.getErrors().get(0).getMessage());
        }

        return result.getData();
    }
}
