package com.example.fstest.service.impl;

import com.example.fstest.entity.Product;
import com.example.fstest.entity.ProductCreateRequest;
import com.example.fstest.repository.ProductRepository;
import com.example.fstest.service.ProductServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

    private final ProductRepository productRepo;


    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> listAll() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product create(ProductCreateRequest createRequest) throws JsonProcessingException {
        Product p = new Product();
        p.setTitle(createRequest.getTitle());
        p.setPrice(createRequest.getPrice());
        p.setVendor(createRequest.getVendor());
        p.setHandle(createRequest.getHandle());
        p.setBody_html(createRequest.getBody_html());
        p.setVariants(createRequest.getVariants());
        return productRepo.save(p);
    }

    @Override
    @Transactional
    public List<Product> saveAll(List<Product> products) {
        productRepo.deleteAllInBatch();
        return productRepo.saveAll(products).subList(0, Math.min(50, products.size()));
    }
}