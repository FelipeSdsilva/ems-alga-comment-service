package com.felipesouls.alga_comment_service.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public ModerationClient moderationClient(RestClientFactory factory,
                                             @Value("${external-apis.monitoring-base-url}") String baseUrl) {
        RestClient restClient = factory.moderationRestClient(baseUrl);
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(ModerationClient.class);
    }
}
