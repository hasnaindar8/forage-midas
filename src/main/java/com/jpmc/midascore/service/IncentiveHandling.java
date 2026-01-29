package com.jpmc.midascore.service;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class IncentiveHandling {
    private final RestTemplate restTemplate;

    public IncentiveHandling(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Incentive getIncentive(Transaction transaction) {
        String url = "http://localhost:8080/incentive";
        URI uri = URI.create(url);
        Incentive incentive = restTemplate.postForObject(uri, transaction, Incentive.class);
        return incentive;
    }
}
