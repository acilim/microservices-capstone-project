package com.microservices.productapplication.web;

import com.microservices.productapplication.client.catalog.ProductItem;
import com.microservices.productapplication.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "return503Unavailable")
    public ResponseEntity getProduct(@PathVariable String id) {
        Optional<ProductItem> productItem = productService.getProduct(id);

        if (!productItem.isPresent()) {
            return new ResponseEntity<>("The requested item is not available", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.of(productItem);
    }

    @GetMapping("products")
    @HystrixCommand(fallbackMethod = "return503Unavailable")
    public ResponseEntity getProductsBySku(@RequestParam(required = false) String sku) {
        List<ProductItem> productItems = productService.getProductsBySku(sku);

        if (productItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    ResponseEntity return503Unavailable(String param) {
        return new ResponseEntity<>("One or more dependent services was not available.",
                HttpStatus.SERVICE_UNAVAILABLE);
    }

}
