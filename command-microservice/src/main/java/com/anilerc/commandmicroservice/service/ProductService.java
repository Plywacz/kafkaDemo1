package com.anilerc.commandmicroservice.service;

import com.anilerc.commandmicroservice.dto.ProductDto;
import com.anilerc.commandmicroservice.event.ProductEvent;
import com.anilerc.commandmicroservice.event.type.EventType;
import com.anilerc.commandmicroservice.model.Product;
import com.anilerc.commandmicroservice.repository.ProductRepository;
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
        Product newProduct = new Product(productDto.name(), productDto.price());
        productRepository.save(newProduct);
        ProductEvent creationEvent = new ProductEvent(EventType.CREATE_PRODUCT, newProduct);
        kafkaTemplate.send("product-event-topic", creationEvent);
    }


}
