package com.marta.sandbox.microservices.shop.service.impl;

import com.marta.sandbox.microservices.shop.model.UserCreateDto;
import com.marta.sandbox.microservices.shop.persistence.domain.User;
import com.marta.sandbox.microservices.shop.persistence.repository.UserRepository;
import com.marta.sandbox.microservices.shop.service.api.UserService;
import com.marta.sandbox.microservices.shop.service.exceptions.ResourceNotFoundException;
import com.marta.sandbox.microservices.shop.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service(value = "userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }

    @Override
    @Transactional
    public void create(UserCreateDto userCreateDto) {
        if (userRepository.existsUserByUsername(userCreateDto.getUsername())) {
            throw new UsernameAlreadyExistsException();
        } else {
            User user = new User();
            user.setUsername(userCreateDto.getUsername());
            user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(ResourceNotFoundException::new);
    }
}
