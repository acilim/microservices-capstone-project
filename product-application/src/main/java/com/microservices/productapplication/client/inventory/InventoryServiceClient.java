package com.microservices.productapplication.client.inventory;

import lombok.NonNull;

import java.util.List;
import java.util.Map;

public interface InventoryServiceClient {

    Map<String, Boolean> getItemsAvailabilities(@NonNull List<String> itemIds);

}
