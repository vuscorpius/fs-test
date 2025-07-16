package com.example.fstest.controller

import com.example.fstest.entity.Product
import com.example.fstest.entity.ProductCreateRequest
import com.example.fstest.service.ProductServiceInterface
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductServiceInterface) {
    @GetMapping("/api/products")
    @ResponseBody
    fun apiList(): List<Product?>? {
        return productService.listAll()
    }

    @PostMapping("/api/products")
    @ResponseBody
    fun apiCreate(@RequestBody request: ProductCreateRequest): Product? {
        return productService.create(request)
    }
}