package com.example.fstest.schedule;

import com.example.fstest.entity.Product;
import com.example.fstest.entity.Variant;
import com.example.fstest.service.ProductServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductSchedule {

    private final ProductServiceInterface productService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    public ProductSchedule(
            ProductServiceInterface productService,
            RestTemplateBuilder builder,
            ObjectMapper objectMapper
    ) {
        this.productService   = productService;
        this.restTemplate     = builder.build();
        this.objectMapper     = objectMapper;
    }

    @Scheduled(initialDelay = 0, fixedRate = 3_600_000)
    public void fetch() throws JsonProcessingException {
        String url = "https://famme.no/products.json";

        ParameterizedTypeReference<Map<String,Object>> mapType =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<Map<String,Object>> resp = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                mapType
        );
        Map<String, Object> root = resp.getBody();
        List<Map<String,Object>> productsJson =
                (List<Map<String,Object>>) root.get("products");
        List<Product> products = new ArrayList<>();
        for (Map<String, Object> m : productsJson.subList(0, Math.min(50, productsJson.size()))) {
            Product p = new Product();
            p.setTitle   (m.get("title").toString());
            p.setPrice   (BigDecimal.valueOf(10));
            p.setHandle   (m.get("handle").toString());
            p.setBody_html   (m.get("body_html").toString());
            p.setVendor(m.get("vendor").toString());

            Object variantsObj = m.get("variants");
            List<Variant> variantList = objectMapper.convertValue(
                    variantsObj,
                    new TypeReference<List<Variant>>() {}
            );
            p.setVariants(variantList);

            products.add(p);
        }

        productService.saveAll(products);
    }
}
