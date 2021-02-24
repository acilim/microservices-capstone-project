package com.microservices.inventoryapplication.service;

import com.microservices.inventoryapplication.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Map<String, Boolean> getProductsAvailability(List<String> ids) {
        return inventoryRepository.getProductsAvailability(ids);
    }
}
