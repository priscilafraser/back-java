package com.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pet.entidades.LigacaoFornecedorProduto;

@Repository
public interface LigacaoFornecedorProdutoRepository extends JpaRepository<LigacaoFornecedorProduto, Long> {
	
	
	@Query("select lp from LigacaoFornecedorProduto lp where lp.fornecedor.id= :idfornecedor")
	List<LigacaoFornecedorProduto> getProdutosPorFornecedor(@Param("idfornecedor") Long idfornecedor);

}
