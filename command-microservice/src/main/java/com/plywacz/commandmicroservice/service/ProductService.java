package com.plywacz.commandmicroservice.service;

import com.plywacz.commandmicroservice.dto.ProductDto;
import com.plywacz.commandmicroservice.event.ProductEvent;
import com.plywacz.commandmicroservice.event.type.EventType;
import com.plywacz.commandmicroservice.model.Product;
import com.plywacz.commandmicroservice.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final KafkaTemplate<EventType, Object> kafkaTemplate;

    public ProductService(ProductRepository productRepository, KafkaTemplate<EventType, Object> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void addProduct(ProductDto productDto) {
        final var newProduct = new Product(productDto.name(), productDto.price());
        productRepository.save(newProduct);
        final var creationEvent = new ProductEvent(EventType.CREATE_PRODUCT, newProduct);
        kafkaTemplate.send("product-event-topic", creationEvent);
    }

}
