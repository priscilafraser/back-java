package com.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pet.entidades.LigacaoFornecedorServico;

@Repository
public interface LigacaoFornecedorServicoRepository extends JpaRepository<LigacaoFornecedorServico, Long>{

	@Query("select ls from LigacaoFornecedorServico ls where ls.fornecedor.id= :idfornecedor")
	List<LigacaoFornecedorServico> getServicosPorFornecedor(@Param("idfornecedor") Long idfornecedor);
	
}
