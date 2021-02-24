package com.microservices.inventoryapplication.web;

import com.microservices.inventoryapplication.domain.ItemsAvailabilities;
import com.microservices.inventoryapplication.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/availability")
    public ItemsAvailabilities getItemAvailability(@RequestParam List<String> ids) {
        return new ItemsAvailabilities(inventoryService.getProductsAvailability(ids));
    }

}
