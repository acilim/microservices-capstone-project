package com.microservices.productapplication.client.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsAvailabilities {

    private Map<String, Boolean> itemsAvailabilities;

}
