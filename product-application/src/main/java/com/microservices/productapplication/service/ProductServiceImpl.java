package com.microservices.productapplication.service;

import com.microservices.productapplication.domain.ItemsAvailabilities;
import com.microservices.productapplication.domain.ProductItem;
import com.microservices.productapplication.domain.ProductItemList;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private static final String CATALOG_SVC_URL = "http://catalog-service/api";
    private static final String INVENTORY_SVC_URL = "http://inventory-service/api";

    private final RestTemplate restTemplate;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ProductItem> getProduct(@NonNull String id) {
        log.info("Invoking catalog service with product id: " + id);
        ResponseEntity<ProductItem> catalogItemResponse =
                restTemplate.getForEntity(CATALOG_SVC_URL + "/catalog/item/{id}",
                        ProductItem.class, id);
        log.info("Catalog item fetched: " + catalogItemResponse);

        if (catalogItemResponse.getBody() == null) {
            return Optional.empty();
        }

        log.info("Invoking inventory service with product id: " + id);
        ResponseEntity<Map> itemAvailabilityResponse =
                restTemplate.getForEntity(INVENTORY_SVC_URL + "/inventory/availability?ids=" + id,
                        Map.class);
        log.info("Item availability fetched: " + itemAvailabilityResponse);

        if (itemAvailabilityResponse.getBody() != null
                && itemAvailabilityResponse.getBody().get(id) != null
                && (Boolean) itemAvailabilityResponse.getBody().get(id)) {
            return Optional.of(catalogItemResponse.getBody());
        }

        return Optional.empty();
    }

    @Override
    public List<ProductItem> getProductsBySku(String sku) {
        log.info("Invoking catalog service with sku: " + sku);
        ResponseEntity<ProductItemList> catalogItemsResponse =
                restTemplate.getForEntity(CATALOG_SVC_URL + "/catalog/items?sku=" + sku,
                        ProductItemList.class);
        log.info("Catalog items fetched: " + catalogItemsResponse.getBody());

        if (catalogItemsResponse.getBody() == null) {
            return Collections.emptyList();
        }

        List<ProductItem> items = catalogItemsResponse.getBody().getProductItems();
        List<String> itemIds = items.stream()
                .map(ProductItem::getId)
                .collect(Collectors.toList());

        log.info("Invoking inventory service with product ids: " + itemIds);
        ResponseEntity<ItemsAvailabilities> itemsAvailabilityResponse =
                restTemplate.getForEntity(INVENTORY_SVC_URL + "/inventory/availability?ids="
                                + String.join(",", itemIds),
                        ItemsAvailabilities.class);
        log.info("Items availabilities fetched: " + itemsAvailabilityResponse);

        if (itemsAvailabilityResponse.getBody() != null) {
            Map<String, Boolean> availability =
                    itemsAvailabilityResponse.getBody().getItemsAvailabilities();
            return items.stream()
                    .filter(item -> availability.get(item.getId()) != null && availability.get(item.getId()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
