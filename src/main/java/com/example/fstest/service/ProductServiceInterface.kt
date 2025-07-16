package com.example.fstest.service

import com.example.fstest.entity.Product
import com.example.fstest.entity.ProductCreateRequest
import com.fasterxml.jackson.core.JsonProcessingException

interface ProductServiceInterface {
    fun create(product: ProductCreateRequest): Product

    fun listAll(page: Int, size: Int): org.springframework.data.domain.Page<Product>

    fun saveAll(products: List<Product?>): List<Product>

    fun delete(id: java.util.UUID)
}
