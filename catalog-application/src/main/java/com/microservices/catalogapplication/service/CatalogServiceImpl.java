package com.microservices.catalogapplication.service;

import com.microservices.catalogapplication.domain.CatalogItem;
import com.microservices.catalogapplication.repository.CatalogRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    private static final long WAIT_TIME = 100L;

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public CatalogItem getProductById(@NonNull String id) {
        log.info("Fetching product by id: " + id);
//        try {
//            log.info("Sleeping...");
//            Thread.sleep(WAIT_TIME);
//            log.info("Sleep finished.");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        CatalogItem product = catalogRepository.getProductById(id);
        log.info("Returning: " + product);
        return product;
    }

    @Override
    public List<CatalogItem> getProductsBySku(String sku) {
        log.info("Fetching products by sku: " + sku);

        return catalogRepository.getProductsBySku(sku);
    }
}
