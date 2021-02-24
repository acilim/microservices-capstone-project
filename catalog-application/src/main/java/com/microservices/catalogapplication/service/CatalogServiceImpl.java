package com.microservices.catalogapplication.service;

import com.microservices.catalogapplication.domain.CatalogItem;
import com.microservices.catalogapplication.repository.CatalogRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public CatalogItem getProductById(@NonNull String id) {
        return catalogRepository.getProductById(id);
    }

    @Override
    public List<CatalogItem> getProductsBySku(String sku) {
        return catalogRepository.getProductsBySku(sku);
    }
}
