package com.marta.sandbox.microservices.shop.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @ManyToOne
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        for (OrderLine line : this.orderLines) {
            sum += line.getTotalPrice();
        }
        return sum;
    }

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate placed;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paid;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate shipped;

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                '}';
    }
}
