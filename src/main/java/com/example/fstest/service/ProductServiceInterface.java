package com.example.fstest.service;

import com.example.fstest.entity.Product;
import com.example.fstest.entity.ProductCreateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProductServiceInterface {
    Product create(ProductCreateRequest product) throws JsonProcessingException;

    List<Product> listAll();

    List<Product> saveAll(List<Product> products);

}
