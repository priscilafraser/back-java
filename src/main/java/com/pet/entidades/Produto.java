package com.pet.entidades;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String produtos;
	
	@OneToOne
	@JoinColumn(name = "idtipoproduto")
	private TipoProduto tipoProduto;  
	
	@OneToOne
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Produto(String produtos, TipoProduto tipoProduto, Categoria categoria) {

		this.produtos = produtos;
		this.tipoProduto = tipoProduto;
		this.categoria = categoria;
	}

	public Produto() {
	}
	
	

}
