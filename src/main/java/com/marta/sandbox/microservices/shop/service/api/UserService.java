package com.marta.sandbox.microservices.shop.service.api;

import com.marta.sandbox.microservices.shop.model.UserCreateDto;
import com.marta.sandbox.microservices.shop.persistence.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create(UserCreateDto user);

    User findByUsername(String username);
}
