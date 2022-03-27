package com.cheeseocean.core;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestCache().disable()
                .headers().disable()
                .sessionManagement().disable()
                .csrf().disable()
                .anonymous().disable()
                .logout().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling().disable()
                .authorizeRequests().anyRequest().permitAll();
    }
}
