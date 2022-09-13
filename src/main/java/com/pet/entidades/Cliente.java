package com.pet.entidades;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	
	@Column(length = 8, nullable = false)
	private String cep;
	
	@Column(length = 9)
	private int numero;
	private String telefone;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String perfil;
	private String senha;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateAt;

	public Cliente(String nome, String email, String cpf, String cep, int numero, String telefone, String logradouro,
			String complemento, String bairro, String cidade, String estado, String perfil, String senha) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.cep = cep;
		this.numero = numero;
		this.telefone = telefone;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.perfil = perfil;
		this.senha = senha;
	}

	
	public Cliente() {
	}
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
	
	
	

}
