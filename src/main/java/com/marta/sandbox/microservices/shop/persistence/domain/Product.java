package com.marta.sandbox.microservices.shop.persistence.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
public class Product {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Version
    @JsonIgnore
    @Column
    private long version;

    @Column
    private String description;

    @Column
    double price;
}
