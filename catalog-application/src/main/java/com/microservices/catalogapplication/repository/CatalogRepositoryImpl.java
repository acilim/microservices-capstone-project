package com.microservices.catalogapplication.repository;

import com.microservices.catalogapplication.domain.CatalogItem;
import com.microservices.catalogapplication.util.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class CatalogRepositoryImpl implements CatalogRepository {

    @Value("${catalog.datafile}")
    private String dataFilePath;

    private List<CatalogItem> items = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        try {
            items = CsvReader.read(CatalogItem.class, dataFilePath);
            log.info(items.size() + " items loaded.");
        } catch (IOException e) {
            log.error("Error loading items! ", e);
        }
    }

    @Override
    public CatalogItem getProductById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CatalogItem> getProductsBySku(String sku) {
        if (!StringUtils.hasText(sku)) {
            return items;
        }
        return items.stream()
                .filter(item -> item.getSku().equals(sku))
                .collect(Collectors.toList());
    }
}
