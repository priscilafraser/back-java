package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.Produto;
import com.pet.entidades.TipoProduto;

public class TipoProdutoDTO {
	
	private Long id;
	
	private String produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public TipoProdutoDTO(String produto) {
		this.produto = produto;
	}

	public TipoProdutoDTO() {

	}
	
	public TipoProdutoDTO(TipoProduto tipoproduto) {
		this.id = tipoproduto.getId();
		this.produto = tipoproduto.getProduto();
		
	}
	
	public static List<TipoProdutoDTO> converteParaDTO(List<TipoProduto> tipoprodutos) {
		List<TipoProdutoDTO> tipoprodutosDTO = new ArrayList<>();
		for(TipoProduto tp: tipoprodutos) {
			tipoprodutosDTO.add(new TipoProdutoDTO(tp));
		}
		return tipoprodutosDTO;
	}
	

}
