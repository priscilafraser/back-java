package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.entidades.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
