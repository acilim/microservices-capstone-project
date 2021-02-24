package com.microservices.catalogapplication.repository;

import com.microservices.catalogapplication.domain.CatalogItem;

import java.util.List;

public interface CatalogRepository {

    CatalogItem getProductById(String id);

    List<CatalogItem> getProductsBySku(String sku);
}
