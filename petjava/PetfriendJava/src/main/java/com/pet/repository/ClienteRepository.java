package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
