package com.example.fstest.controller;

import com.example.fstest.entity.Product;
import com.example.fstest.entity.ProductCreateRequest;
import com.example.fstest.service.ProductServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    private final ProductServiceInterface productService;

    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }



    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> apiList() {
        return productService.listAll();
    }

    @PostMapping("/api/products")
    @ResponseBody
    public Product apiCreate(@RequestBody ProductCreateRequest request) throws JsonProcessingException {
        return productService.create(request);
    }
}