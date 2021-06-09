package com.desafio.labs.springfavs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.labs.springfavs.domain.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
 
	 
}
