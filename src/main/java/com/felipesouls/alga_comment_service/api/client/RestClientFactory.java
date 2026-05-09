package com.felipesouls.alga_comment_service.api.client;

import java.time.Duration;

import com.felipesouls.alga_comment_service.api.controllers.exceptions.ModerationClientBadGatewayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClient moderationRestClient(String baseUrl) {
        return builder.baseUrl(baseUrl)
                .requestFactory(genereteClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, ((request, response) -> {
                    throw new ModerationClientBadGatewayException(
                            "Error while calling Moderation service. Status: " + response.getStatusCode()
                    );
                }))
                .build();
    }

    private ClientHttpRequestFactory genereteClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(5));
        return factory;
    }
}

