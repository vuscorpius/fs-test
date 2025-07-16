package com.example.fstest.schedule

import com.example.fstest.entity.Product
import com.example.fstest.entity.Variant
import com.example.fstest.service.ProductServiceInterface
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.lang.Double.min
import java.math.BigDecimal
import java.util.*

@Component
class ProductSchedule(private val productService: ProductServiceInterface,
                      builder: RestTemplateBuilder,
                      private val objectMapper: ObjectMapper
) {
    private val restTemplate: RestTemplate = builder.build()

    @Scheduled(initialDelay = 0, fixedRate = 3600000)
    fun fetch() {
        val url = "https://famme.no/products.json"

        val mapType: ParameterizedTypeReference<Map<String?, Any?>> =
                object : ParameterizedTypeReference<Map<String?, Any?>>() {}

        val resp = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                mapType
        )
        val root = resp.body
        val productsJson =
                root["products"] as List<Map<String, Any>>?
        val products: MutableList<Product?> = ArrayList()
        for (m in productsJson!!.subList(0, min(50.0, productsJson.size.toDouble()).toInt())) {
            val p = Product()
            p.title = m["title"].toString()
            p.price = BigDecimal.valueOf(10)
            p.handle = m["handle"].toString()
            p.body_html = m["body_html"].toString()
            p.vendor = m["vendor"].toString()

            val variantsObj = m["variants"]
            val variantList = objectMapper.convertValue(
                    variantsObj,
                    object : TypeReference<List<Variant?>?>() {}
            )
            p.variants = variantList

            products.add(p)
        }

        productService.saveAll(products)
    }
}
