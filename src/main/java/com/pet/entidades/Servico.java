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
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String servicos;
	
	@ManyToOne
	@JoinColumn(name = "idtiposervico")	
	private TipoServico tipoServico;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServicos() {
		return servicos;
	}

	public void setServicos(String servicos) {
		this.servicos = servicos;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Servico(String servicos, TipoServico tipoServico, Categoria categoria) {
		this.servicos = servicos;
		this.tipoServico = tipoServico;
		this.categoria = categoria;
	}

	public Servico() {
		
	}

	
}
