package com.marta.sandbox.microservices.shop.persistence.repository;

import com.marta.sandbox.microservices.shop.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername (String username);

    boolean existsUserByUsername (String username);
}
