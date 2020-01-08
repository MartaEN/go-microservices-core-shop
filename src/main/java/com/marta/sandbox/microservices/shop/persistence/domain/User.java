package com.marta.sandbox.microservices.shop.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name="users")
public class User {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    @JsonIgnore
    private String id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "password_hash", nullable = false, length = 60)
    @JsonIgnore
    private String password;
}
