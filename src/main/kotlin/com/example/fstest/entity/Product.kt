package com.example.fstest.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "product")
class Product {
    @Id
    @GeneratedValue
    var id: UUID? = null

    var title: String? = null

    var price: BigDecimal? = null

    var vendor: String? = null

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    var variants: List<Variant?>? = null

    var handle: String? = null

    var body_html: String? = null
}