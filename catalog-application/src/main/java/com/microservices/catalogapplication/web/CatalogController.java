package com.microservices.catalogapplication.web;

import com.microservices.catalogapplication.domain.CatalogItem;
import com.microservices.catalogapplication.domain.CatalogItemList;
import com.microservices.catalogapplication.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/item/{id}")
    public CatalogItem getItem(@PathVariable String id) {
        return catalogService.getProductById(id);
    }

    @GetMapping("items")
    public CatalogItemList getItems(@RequestParam(required = false) String sku) {
        return new CatalogItemList(catalogService.getProductsBySku(sku));
    }

}
