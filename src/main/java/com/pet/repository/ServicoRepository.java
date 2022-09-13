package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.entidades.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

}
