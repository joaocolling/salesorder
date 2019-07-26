/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.salesorder.configuration.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author joao
 */
@EnableWebMvc
@Configuration
public class PageAndSortConfiguration implements WebMvcConfigurer{


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageAndSortInterceptor());
    }

    @Bean(name = "customPageRequest")
    @RequestScope
    public CustomPageRequest customPageRequest() {
        return new CustomPageRequest();
    }
    
    @Bean
    public PageAndSortInterceptor pageAndSortInterceptor() {
        return new PageAndSortInterceptor(customPageRequest());
    }

}
