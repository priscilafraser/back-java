package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.Produto;
import com.pet.repository.ProdutoRepository;
import com.pet.services.dto.ProdutoDTO;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repo;
	
	public ProdutoDTO salvar(Produto produto) {
		Produto pr = repo.save(produto);
		ProdutoDTO produtoDTO = new ProdutoDTO(pr); 
		return produtoDTO;
	}
	
	public List<ProdutoDTO> consultarProduto() {
		List<Produto> produto = repo.findAll();
		List<ProdutoDTO> produtoDTO = new ArrayList();
		for(Produto pr: produto) {
			produtoDTO.add(new ProdutoDTO(pr));   
		}
		return produtoDTO;	
	}
	
	public ProdutoDTO consultarProdutoPorId(Long idproduto) {
		Optional<Produto> opproduto = repo.findById(idproduto);
		Produto pr = opproduto.orElseThrow(() -> new EntityNotFoundException("Produto nao encontrado"));
		return new ProdutoDTO(pr);	
	}
	
	public ProdutoDTO alterarProduto(Long idproduto, Produto produto) {
		ProdutoDTO pr = consultarProdutoPorId(idproduto);
		pr.setProdutos(produto.getProdutos());
		pr.setTipoProduto(produto.getTipoProduto());
		pr.setCategoria(produto.getCategoria());
		return new ProdutoDTO(repo.save(produto));
	}
	
	public void excluirProduto(Long idproduto) {
		repo.deleteById(idproduto);
	}
	
	
}
