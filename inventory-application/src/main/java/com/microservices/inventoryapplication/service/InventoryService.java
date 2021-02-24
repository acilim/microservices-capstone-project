package com.microservices.inventoryapplication.service;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    Map<String, Boolean> getProductsAvailability(List<String> ids);
}
