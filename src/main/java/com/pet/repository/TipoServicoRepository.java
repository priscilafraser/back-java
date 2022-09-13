package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.entidades.TipoServico;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long>{

}
