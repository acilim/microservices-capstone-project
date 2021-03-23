package com.microservices.productapplication.client.catalog;

import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CatalogServiceClient {

    Optional<ProductItem> getProduct(@NonNull String id);

    List<ProductItem> getProductsBySku(String sku);
    
}
