package com.example.fstest.service.impl

import com.example.fstest.entity.Product
import com.example.fstest.entity.ProductCreateRequest
import com.example.fstest.repository.ProductRepository
import com.example.fstest.service.ProductServiceInterface
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID
import org.springframework.transaction.annotation.Transactional

@Service
open class ProductServiceImpl(private val productRepo: ProductRepository) : ProductServiceInterface {
    override fun listAll(page: Int, size: Int): Page<Product> {
        val pageRequest = PageRequest.of(page, size)
        return productRepo.findAll(pageRequest)
    }

    @Transactional
    override fun create(createRequest: ProductCreateRequest): Product {
        val p = Product()
        p.title = createRequest.title
        p.price = createRequest.price
        p.vendor = createRequest.vendor
        p.handle = createRequest.handle
        p.body_html = createRequest.bodyHtml
        p.variants = createRequest.variants
        return productRepo.save(p)
    }

    @Transactional
    override fun saveAll(products: List<Product?>): List<Product> {
        val saved: List<Product?> = productRepo.saveAll(products)
        return saved.filterNotNull().take(50)
    }

    @Transactional
    override fun delete(id: UUID) {
        productRepo.deleteById(id)
    }

    override fun findById(id: UUID): Product? {
        return productRepo.findById(id).orElse(null)
    }

}