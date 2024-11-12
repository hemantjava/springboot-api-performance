package com.hemanttechie.service.impl;

import com.hemanttechie.entity.Product;
import com.hemanttechie.repository.ProductRepository;
import com.hemanttechie.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        log.info("Service request to fetch product by id: {}", id);
        addDelay();
        return productRepository.findById(id).orElse(null);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
