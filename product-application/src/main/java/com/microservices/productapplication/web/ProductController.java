package com.microservices.productapplication.web;

import com.microservices.productapplication.domain.ProductItem;
import com.microservices.productapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductItem> getProduct(@PathVariable String id) {
        Optional<ProductItem> productItem = productService.getProduct(id);

        if (!productItem.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.of(productItem);
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductItem>> getProductsBySku(@RequestParam(required = false) String sku) {
        List<ProductItem> productItems = productService.getProductsBySku(sku);

        if (productItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }

}
