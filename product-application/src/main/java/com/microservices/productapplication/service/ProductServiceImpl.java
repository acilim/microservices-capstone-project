package com.microservices.productapplication.service;

import com.microservices.productapplication.client.catalog.CatalogServiceClient;
import com.microservices.productapplication.client.catalog.ProductItem;
import com.microservices.productapplication.client.inventory.InventoryServiceClient;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private CatalogServiceClient catalogClient;

    private InventoryServiceClient inventoryClient;

    @Autowired
    public ProductServiceImpl(CatalogServiceClient catalogClient,
                              InventoryServiceClient inventoryClient) {
        this.catalogClient = catalogClient;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public Optional<ProductItem> getProduct(@NonNull String id) {
        Optional<ProductItem> catalogItem = catalogClient.getProduct(id);

        if (!catalogItem.isPresent()) {
            return Optional.empty();
        }

        Map<String, Boolean> itemAvailability =
                inventoryClient.getItemsAvailabilities(Collections.singletonList(id));

        if (itemAvailability.get(id) != null && itemAvailability.get(id)) {
            return catalogItem;
        }

        return Optional.empty();
    }

    @Override
    public List<ProductItem> getProductsBySku(String sku) {
        List<ProductItem> items = catalogClient.getProductsBySku(sku);

        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        List<String> itemIds = items.stream()
                .map(ProductItem::getId)
                .collect(Collectors.toList());

        Map<String, Boolean> itemsAvailabilities = inventoryClient.getItemsAvailabilities(itemIds);

        return items.stream()
                .filter(item -> itemsAvailabilities.get(item.getId()) != null
                        && itemsAvailabilities.get(item.getId()))
                .collect(Collectors.toList());
    }
}
