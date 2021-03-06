package com.microservices.productapplication.service;

import com.microservices.productapplication.client.catalog.ProductItem;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<ProductItem> getProduct(@NonNull String id);

    List<ProductItem> getProductsBySku(String sku);
}
