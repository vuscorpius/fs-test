package com.example.fstest.repository

import com.example.fstest.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface ProductRepository : JpaRepository<Product, UUID>
