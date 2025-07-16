package com.example.fstest.entity

import java.math.BigDecimal


class ProductCreateRequest {
    var title: String? = null
    var price: BigDecimal? = null
    var vendor: String? = null
    var body_html: String? = null
    var handle: String? = null

    var variants: List<Variant>? = null
}

