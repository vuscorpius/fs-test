package com.example.fstest.service.impl

import com.example.fstest.entity.Product
import com.example.fstest.entity.ProductCreateRequest
import com.example.fstest.repository.ProductRepository
import com.example.fstest.service.ProductServiceInterface
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.min

@Service
class ProductServiceImpl(private val productRepo: ProductRepository) : ProductServiceInterface {
    override fun listAll(): List<Product?> {
        return productRepo.findAll()
    }

    @Transactional
    @Throws(JsonProcessingException::class)
    override fun create(createRequest: ProductCreateRequest): Product {
        val p = Product()
        p.title = createRequest.title
        p.price = createRequest.price
        p.vendor = createRequest.vendor
        p.handle = createRequest.handle
        p.body_html = createRequest.body_html
        p.variants = createRequest.variants
        return productRepo.save(p)
    }

    @Transactional
    override fun saveAll(products: List<Product?>): List<Product> {
        productRepo.deleteAllInBatch()
        return productRepo.saveAll(products).subList(0, min(50.0, products.size.toDouble()).toInt())
    }
}