package com.microservices.inventoryapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ItemsAvailabilities {

    private Map<String, Boolean> itemsAvailabilities;

}
