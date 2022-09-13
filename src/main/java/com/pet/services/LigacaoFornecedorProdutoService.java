package com.pet.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.LigacaoFornecedorProduto;
import com.pet.repository.LigacaoFornecedorProdutoRepository;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;

@Service
public class LigacaoFornecedorProdutoService {

	@Autowired
	LigacaoFornecedorProdutoRepository repo;
	
	public LigacaoFornecedorProdutoDTO salvar(LigacaoFornecedorProduto ligacaofornecedorproduto) {
		LigacaoFornecedorProduto ligforprod = repo.save(ligacaofornecedorproduto);
		LigacaoFornecedorProdutoDTO ligacaoFornecedorProdutoDTO = new LigacaoFornecedorProdutoDTO(ligforprod); 
		return ligacaoFornecedorProdutoDTO;
	}
	
	public List<LigacaoFornecedorProdutoDTO> consultarLigacaoFornecedorProduto() {
		List<LigacaoFornecedorProduto> ligacaoFornecedorProduto = repo.findAll();
		List<LigacaoFornecedorProdutoDTO> ligacaoFornecedorProdutoDTO = new ArrayList();
		for(LigacaoFornecedorProduto ligforprod: ligacaoFornecedorProduto) {
			ligacaoFornecedorProdutoDTO.add(new LigacaoFornecedorProdutoDTO(ligforprod));    //recebe como construtor o ct
		}
		return ligacaoFornecedorProdutoDTO;	
	}
	
	public LigacaoFornecedorProdutoDTO consultarLigacaoFornecedorProdutoPorId(Long idligacaofornecedorproduto) {
		Optional<LigacaoFornecedorProduto> opligacaofornecedorproduto = repo.findById(idligacaofornecedorproduto);
		LigacaoFornecedorProduto ligforprod = opligacaofornecedorproduto.orElseThrow(() -> new EntityNotFoundException("Contato nao encontrado"));
		return new LigacaoFornecedorProdutoDTO(ligforprod);	
	}
	
	public LigacaoFornecedorProdutoDTO alterarLigacaoFornecedorProduto(Long idligacaofornecedorproduto, LigacaoFornecedorProduto ligacaofornecedorproduto) {
		LigacaoFornecedorProdutoDTO ligforprod = consultarLigacaoFornecedorProdutoPorId(idligacaofornecedorproduto);
		ligforprod.setPreco(ligacaofornecedorproduto.getPreco());
		ligforprod.setImagem(ligacaofornecedorproduto.getImagem());
		ligforprod.setDescricao(ligacaofornecedorproduto.getDescricao());
		ligforprod.setFornecedor(ligacaofornecedorproduto.getFornecedor());
		ligforprod.setTipoproduto(ligacaofornecedorproduto.getTipoproduto());
		ligforprod.setCategoria(ligacaofornecedorproduto.getCategoria());
		return new LigacaoFornecedorProdutoDTO(repo.save(ligacaofornecedorproduto));
	}
	
	public void excluirLigacaoFornecedorProduto(Long idligacaofornecedorproduto) {
		repo.deleteById(idligacaofornecedorproduto);
	}
	
	///////////////////////PERSONALIZADO
	public List<LigacaoFornecedorProdutoDTO> consultarProdutosPorFornecedor(Long idfornecedor) {
		return LigacaoFornecedorProdutoDTO.converteParaDTO(repo.getProdutosPorFornecedor(idfornecedor));
	}
	
	
}
