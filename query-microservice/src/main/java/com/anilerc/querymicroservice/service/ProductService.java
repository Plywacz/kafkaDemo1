package com.anilerc.querymicroservice.service;

import com.anilerc.querymicroservice.dto.ProductDto;
import com.anilerc.querymicroservice.event.ProductEvent;
import com.anilerc.querymicroservice.event.ProductEventType;
import com.anilerc.querymicroservice.model.Product;
import com.anilerc.querymicroservice.repository.ProductRepository;
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
        Product incomingProduct = productEvent.getProduct();
        switch (productEvent.getEventType()) {
            case ProductEventType.CREATE_PRODUCT -> productRepository.save(incomingProduct);
            // Other mutation types can be processed as well!
        }
    }
}
