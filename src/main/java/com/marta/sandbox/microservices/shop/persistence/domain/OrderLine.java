package com.marta.sandbox.microservices.shop.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orderLines")
public class OrderLine {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPrimaryKey pk;

    private int quantity;

    private double price;

    public OrderLine (Order order, Product product, int quantity) {
        this.pk = new OrderProductPrimaryKey();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public double getTotalPrice() {
        return price * quantity;
    }

}
