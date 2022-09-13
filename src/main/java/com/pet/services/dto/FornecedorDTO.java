package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.pet.entidades.Categoria;
import com.pet.entidades.Fornecedor;

public class FornecedorDTO {

	
	private Long id;
	private String razaosocial;
	private String cnpj;
	private String telefone;
	private String email;
	private String cep;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String perfil;
	private String senha;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public FornecedorDTO() {
		
	}
	public FornecedorDTO(String razaosocial, String cnpj, String telefone, String email, String cep, String logradouro,
			int numero, String complemento, String bairro, String cidade, String estado, String perfil, String senha) {
		
		this.razaosocial = razaosocial;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.perfil = perfil;
		this.senha = senha;
	}
	
	public FornecedorDTO(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.razaosocial = fornecedor.getRazaosocial();
		this.cnpj = fornecedor.getCnpj();
		this.telefone = fornecedor.getTelefone();
		this.email = fornecedor.getEmail();
		this.cep = fornecedor.getCep();
		this.logradouro = fornecedor.getLogradouro();
		this.numero = fornecedor.getNumero();
		this.complemento = fornecedor.getComplemento();
		this.bairro = fornecedor.getBairro();
		this.cidade = fornecedor.getCidade();
		this.estado = fornecedor.getEstado();
		this.perfil = fornecedor.getPerfil();
		this.senha = fornecedor.getSenha();
		
		
	}
	
	public static List<FornecedorDTO> converteParaDTO(List<Fornecedor> fornecedores) {
		List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
		for(Fornecedor fr: fornecedores) {
			fornecedoresDTO.add(new FornecedorDTO(fr));
		}
		return fornecedoresDTO;
	}
	
	
	
	
	
	
	
	
}
