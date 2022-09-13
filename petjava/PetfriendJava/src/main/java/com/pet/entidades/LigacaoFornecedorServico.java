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
public class LigacaoFornecedorServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float preco;
	private String descricao;
	private String horainicial;
	private String horafinal;
	
	@ManyToOne
	@JoinColumn(name = "idfornecedor")
	private Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "idtiposervico")
	private TipoServico tiposervico;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	
	public TipoServico getTiposervico() {
		return tiposervico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


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

	@PrePersist           //nao recebe Instant, recebe um Instant
	public void setCreatedAt() {
		this.createdAt = Instant.now();
	}
	
	
	
	public String getHorainicial() {
		return horainicial;
	}

	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}

	public String getHorafinal() {
		return horafinal;
	}

	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
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


	public LigacaoFornecedorServico() {
		
	}

	public LigacaoFornecedorServico(float preco, String descricao, String horainicial, String horafinal,
			Fornecedor fornecedor, TipoServico tiposervico, Categoria categoria) {
		super();
		this.preco = preco;
		this.descricao = descricao;
		this.horainicial = horainicial;
		this.horafinal = horafinal;
		this.fornecedor = fornecedor;
		this.tiposervico = tiposervico;
		this.categoria = categoria;
	}

	
	
	

	
	
	
	
}
