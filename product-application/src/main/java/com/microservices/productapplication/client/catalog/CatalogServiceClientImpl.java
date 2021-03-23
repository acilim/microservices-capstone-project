package com.microservices.productapplication.client.catalog;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CatalogServiceClientImpl implements CatalogServiceClient {

    private static final String CATALOG_SVC_URL = "http://catalog-service/api";

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ProductItem> getProduct(@NonNull String id) {
        log.info("Invoking catalog service with catalog id: " + id);
        ResponseEntity<ProductItem> catalogItemResponse =
                restTemplate.getForEntity(CATALOG_SVC_URL + "/catalog/item/{id}",
                        ProductItem.class, id);
        log.info("Catalog item fetched: " + catalogItemResponse);

        if (catalogItemResponse.getBody() == null) {
            return Optional.empty();
        }

        return Optional.of(catalogItemResponse.getBody());
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

        return catalogItemsResponse.getBody().getProductItems();
    }
}
