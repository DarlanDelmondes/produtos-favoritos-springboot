package com.desafio.labs.springfavs.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.desafio.labs.springfavs.domain.Product;
import com.desafio.labs.springfavs.repository.ClienteRepository;

@Component
public class SpringDataRestCustomization 
implements RepositoryRestConfigurer 
{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Product.class,ClienteRepository.class);
    }
}