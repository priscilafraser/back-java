package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.Produto;
import com.pet.entidades.TipoProduto;

public class ProdutoDTO {
	
	private Long id;
	private String produtos;
	private TipoProduto tipoProduto;
	private Categoria categoria;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProdutos() {
		return produtos;
	}
	public void setProdutos(String produtos) {
		this.produtos = produtos;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	public ProdutoDTO() {
		
	}
	public ProdutoDTO(String produtos, TipoProduto tipoProduto, Categoria categoria) {
		
		this.produtos = produtos;
		this.tipoProduto = tipoProduto;
		this.categoria = categoria;
	}

	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.produtos = produto.getProdutos();
		this.tipoProduto = produto.getTipoProduto();
		this.categoria = produto.getCategoria();
		
	}
	
	public static List<ProdutoDTO> converteParaDTO(List<Produto> produtos) {
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		for(Produto pr: produtos) {
			produtosDTO.add(new ProdutoDTO(pr));
		}
		return produtosDTO;
	}
	
	

}
