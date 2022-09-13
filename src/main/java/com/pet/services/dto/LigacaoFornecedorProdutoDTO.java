package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.Fornecedor;
import com.pet.entidades.LigacaoFornecedorProduto;
import com.pet.entidades.Storage;
import com.pet.entidades.TipoProduto;

public class LigacaoFornecedorProdutoDTO {
	
	private Long id;
	private float preco;
	private String imagem;
	private String descricao;
	
	private Fornecedor fornecedor;
	private TipoProduto tipoproduto;
	private Categoria categoria;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
	
	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}
	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public LigacaoFornecedorProdutoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public LigacaoFornecedorProdutoDTO(float preco, String imagem, String descricao, Fornecedor fornecedor,
			TipoProduto tipoproduto, Categoria categoria) {
		super();
		this.preco = preco;
		this.imagem = imagem;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.tipoproduto = tipoproduto;
		this.categoria = categoria;
	}
	public LigacaoFornecedorProdutoDTO(LigacaoFornecedorProduto ligacaofornecedorproduto) {
		this.id = ligacaofornecedorproduto.getId();
		this.preco = ligacaofornecedorproduto.getPreco();
		this.imagem = ligacaofornecedorproduto.getImagem();
		this.categoria = ligacaofornecedorproduto.getCategoria();
		this.descricao = ligacaofornecedorproduto.getDescricao();
		this.fornecedor = ligacaofornecedorproduto.getFornecedor();
		this.tipoproduto = ligacaofornecedorproduto.getTipoproduto();
		
		
	}
	
	public static List<LigacaoFornecedorProdutoDTO> converteParaDTO(List<LigacaoFornecedorProduto> ligacaofornecedorproduto) {
		List<LigacaoFornecedorProdutoDTO> ligacaoFornecedorProdutoDTO = new ArrayList<>();
		for(LigacaoFornecedorProduto ligforprod: ligacaofornecedorproduto) {
			ligacaoFornecedorProdutoDTO.add(new LigacaoFornecedorProdutoDTO(ligforprod));
		}
		return ligacaoFornecedorProdutoDTO;
	}
	
	
	
	

}
