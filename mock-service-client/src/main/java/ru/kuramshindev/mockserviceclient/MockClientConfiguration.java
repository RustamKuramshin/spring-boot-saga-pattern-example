package ru.kuramshindev.mockserviceclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ConditionalOnProperty(prefix = "mock-service", name = "base-url")
@ComponentScan("ru.kuramshindev.mockserviceclient.services")
public class MockClientConfiguration {

    @Value( "${mock-service.base-url}")
    private String mockServiceBaseUrl;

    @Bean
    @Qualifier("mockServiceRestClient")
    public RestClient mockServiceRestClient() {
        return RestClient.builder()
                .baseUrl(mockServiceBaseUrl)
                .build();
    }
}
