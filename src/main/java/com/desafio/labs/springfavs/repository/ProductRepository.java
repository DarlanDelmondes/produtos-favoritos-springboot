package com.desafio.labs.springfavs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.desafio.labs.springfavs.domain.Product;

//@Repository
@RepositoryRestResource(collectionResourceRel = "products",path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
 
}
