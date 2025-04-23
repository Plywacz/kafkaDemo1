package com.plywacz.querymicroservice.event;

import com.plywacz.querymicroservice.model.Product;

public class ProductEvent {
    private ProductEventType productEventType;
    private Product product;

    public ProductEvent(ProductEventType productEventType, Product product) {
        this.productEventType = productEventType;
        this.product = product;
    }

    public ProductEvent() {
    }

    public ProductEventType getEventType() {
        return productEventType;
    }

    public void setEventType(ProductEventType productEventType) {
        this.productEventType = productEventType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
