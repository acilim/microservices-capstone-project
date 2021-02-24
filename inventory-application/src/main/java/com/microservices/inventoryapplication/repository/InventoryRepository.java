package com.microservices.inventoryapplication.repository;

import java.util.List;
import java.util.Map;

public interface InventoryRepository {

    Map<String, Boolean> getProductsAvailability(List<String> ids);

}
