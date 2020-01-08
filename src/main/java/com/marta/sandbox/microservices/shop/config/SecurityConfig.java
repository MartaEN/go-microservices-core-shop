package com.marta.sandbox.microservices.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.apply(daoAuthenticationConfigurer());
        auth.apply(inMemoryUserDetailsManagerConfigurer());
    }

    @Bean
    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> daoAuthenticationConfigurer() {
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> configurer =
                new DaoAuthenticationConfigurer<>(userDetailsService);
        configurer.passwordEncoder(encoder());
        return configurer;
    }

    @Bean
    public InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryUserDetailsManagerConfigurer() {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = new InMemoryUserDetailsManagerConfigurer<>();
        configurer.passwordEncoder(encoder());
        configurer.withUser("admin")
                .password(encoder().encode("admin"))
                .credentialsExpired(false)
                .accountExpired(false)
                .accountLocked(false)
                .roles("ADMIN");
        return configurer;
    }

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .httpBasic();
    }
}
