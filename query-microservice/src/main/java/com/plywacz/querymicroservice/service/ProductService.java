package com.plywacz.querymicroservice.service;

import com.plywacz.querymicroservice.dto.ProductDto;
import com.plywacz.querymicroservice.event.ProductEvent;
import com.plywacz.querymicroservice.event.ProductEventType;
import com.plywacz.querymicroservice.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
     return productRepository.findAll().stream().map(product -> new ProductDto(product.getName(), product.getPrice())).toList();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        final var incomingProduct = productEvent.getProduct();
        switch (productEvent.getEventType()) {
            case ProductEventType.CREATE_PRODUCT -> productRepository.save(incomingProduct);
            // Other mutation types can be processed as well!
        }
    }
}
