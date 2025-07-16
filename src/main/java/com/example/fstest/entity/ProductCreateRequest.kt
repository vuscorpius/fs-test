package com.example.fstest.entity

import java.math.BigDecimal


data class ProductCreateRequest(
    val title: String,
    val price: BigDecimal,
    val vendor: String?,
    val handle: String?,
    val bodyHtml: String?,
    val variants: List<Variant> = emptyList()
)

