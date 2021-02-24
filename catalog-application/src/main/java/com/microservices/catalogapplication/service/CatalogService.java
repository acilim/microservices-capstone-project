package com.microservices.catalogapplication.service;

import com.microservices.catalogapplication.domain.CatalogItem;

import java.util.List;

public interface CatalogService {

    CatalogItem getProductById(String id);

    List<CatalogItem> getProductsBySku(String sku);
}
