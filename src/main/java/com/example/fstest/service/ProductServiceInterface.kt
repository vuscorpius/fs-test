package com.example.fstest.service

import com.example.fstest.entity.Product
import com.example.fstest.entity.ProductCreateRequest
import com.fasterxml.jackson.core.JsonProcessingException

interface ProductServiceInterface {
    fun create(product: ProductCreateRequest): Product

    fun listAll(): List<Product?>

    fun saveAll(products: List<Product?>): List<Product>
}
