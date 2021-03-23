package com.microservices.productapplication.client.inventory;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class InventoryServiceClientImpl implements InventoryServiceClient {

    private static final String INVENTORY_SVC_URL = "http://inventory-service/api";

    private final RestTemplate restTemplate;

    @Autowired
    public InventoryServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, Boolean> getItemsAvailabilities(@NonNull List<String> itemIds) {
        log.info("Invoking inventory service with product ids: " + itemIds);
        ResponseEntity<ItemsAvailabilities> itemsAvailabilityResponse =
                restTemplate.getForEntity(INVENTORY_SVC_URL + "/inventory/availability?ids="
                                + String.join(",", itemIds),
                        ItemsAvailabilities.class);
        log.info("Items availabilities fetched: " + itemsAvailabilityResponse);

        if (itemsAvailabilityResponse.getBody() != null) {
            return itemsAvailabilityResponse.getBody().getItemsAvailabilities();
        }

        return Collections.emptyMap();
    }
}
