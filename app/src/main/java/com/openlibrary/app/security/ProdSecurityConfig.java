package com.openlibrary.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Profile("production")
@Configuration
public class ProdSecurityConfig extends SecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**/login").permitAll()
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .csrf().disable()
                .cors().disable()
                .formLogin();
    }
}
