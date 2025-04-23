package com.plywacz.commandmicroservice.event;

import com.plywacz.commandmicroservice.event.type.EventType;
import com.plywacz.commandmicroservice.model.Product;

public class ProductEvent {
    private EventType eventType;
    private Product product;

    public ProductEvent(EventType eventType, Product product) {
        this.eventType = eventType;
        this.product = product;
    }

    public ProductEvent() {
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
