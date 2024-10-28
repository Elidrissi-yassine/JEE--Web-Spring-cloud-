package com.example.Service3;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductServiceConfig config;

    public ProductController(ProductServiceConfig config) {
        this.config = config;
    }

    @GetMapping("/config")
    public String getConfig() {
        return "Database URL: " + config.getDatabaseUrl() +
                ", Inventory Service URL: " + config.getInventoryServiceUrl();
    }
}
