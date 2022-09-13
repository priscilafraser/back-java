package com.pet.entidades;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class LigacaoFornecedorProduto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float preco;
	private String imagem;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "idfornecedor")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "idtipoproduto")
	private TipoProduto tipoproduto;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateAt;
	
	public Instant getUpdateAt() {
		return updateAt;
	}
	
	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = Instant.now();
	}
	
		
	public LigacaoFornecedorProduto() {  
		
	}


	
	public LigacaoFornecedorProduto(float preco, String imagem, Categoria categoria, String descricao, Fornecedor fornecedor,
			TipoProduto tipoproduto) {
		this.preco = preco;
		this.imagem = imagem;
		this.categoria = categoria;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.tipoproduto = tipoproduto;
		
	}

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


}
