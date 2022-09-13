package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Cliente;

public class ClienteDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String cep;
	private int numero;
	private String telefone;
	private String logradouro;
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
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	public ClienteDTO(String nome, String email, String cpf, String cep, int numero, String telefone, String logradouro,
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
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.cep = cliente.getCep();
		this.numero = cliente.getNumero();
		this.telefone = cliente.getTelefone();
		this.logradouro = cliente.getLogradouro();
		this.complemento = cliente.getComplemento();
		this.bairro = cliente.getBairro();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
		this.perfil = cliente.getPerfil();
		this.senha = cliente.getSenha();
	}
	
	public static List<ClienteDTO> converteParaDTO(List<Cliente> clientes) {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for(Cliente fr: clientes) {
			clientesDTO.add(new ClienteDTO(fr));
		}
		return clientesDTO;
	}
	
	
	
	
	

}
