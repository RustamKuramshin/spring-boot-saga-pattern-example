package ru.kuramshindev.mockserviceclient.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MockServiceClient {

    private final RestClient mockServiceRestClient;

    public MockServiceClient(@Qualifier("mockServiceRestClient") RestClient restClient) {
        this.mockServiceRestClient = restClient;
    }

    public String makePayment() {
        return post("/api/v1/payments");
    }

    public String cancelPayment() {
        return post("/api/v1/payments/cancel");
    }

    public String reserveInventory() {
        return post("/api/v1/inventory/reserve");
    }

    public String releaseInventory() {
        return post("/api/v1/inventory/release");
    }

    public String createShipping() {
        return post("/api/v1/shipping/create");
    }

    public String cancelShipping() {
        return post("/api/v1/shipping/cancel");
    }

    private String post(String path) {
        return mockServiceRestClient.post()
                .uri(path)
                .retrieve()
                .body(String.class);
    }
}
