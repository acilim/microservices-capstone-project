package com.microservices.inventoryapplication.repository;

import com.microservices.inventoryapplication.domain.CatalogItem;
import com.microservices.inventoryapplication.util.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class InventoryRepositoryImpl implements InventoryRepository {

    @Value("${inventory.datafile}")
    private String dataFilePath;

    private Map<String, Boolean> itemsAvailability = new HashMap<>();

    @PostConstruct
    public void loadData() {
        try {
            List<CatalogItem> items = CsvReader.read(CatalogItem.class, dataFilePath);
            itemsAvailability = items.stream()
            .collect(Collectors.toMap(CatalogItem::getId, item -> new Random().nextBoolean()));
            log.info(itemsAvailability.size() + " items loaded.");
        } catch (IOException e) {
            log.error("Error loading items! ", e);
        }
    }

    @Override
    public Map<String, Boolean> getProductsAvailability(List<String> ids) {
        return ids.stream()
                .collect(Collectors.toMap(id -> id, id -> itemsAvailability.get(id)));
    }
}
