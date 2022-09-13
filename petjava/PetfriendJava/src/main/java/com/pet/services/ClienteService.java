package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.Cliente;
import com.pet.repository.ClienteRepository;
import com.pet.services.dto.ClienteDTO;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	public ClienteDTO salvar(Cliente cliente) {
		Cliente cli = repo.save(cliente);
		ClienteDTO clienteDTO = new ClienteDTO(cli); 
		return clienteDTO;
	}
	
	public List<ClienteDTO> consultarCliente() {
		List<Cliente> cliente = repo.findAll();
		List<ClienteDTO> clienteDTO = new ArrayList();
		for(Cliente cli: cliente) {
			clienteDTO.add(new ClienteDTO(cli));    //recebe como construtor o ct
		}
		return clienteDTO;	
	}
	
	public ClienteDTO consultarClientePorId(Long idcliente) {
		Optional<Cliente> opcliente = repo.findById(idcliente);
		Cliente cli = opcliente.orElseThrow(() -> new EntityNotFoundException("Cliente nao encontrado"));
		return new ClienteDTO(cli);	
	}
	
	public ClienteDTO alterarCliente(Long idcliente, Cliente cliente) {
		ClienteDTO cli = consultarClientePorId(idcliente);
		cli.setNome(cliente.getNome());
		cli.setEmail(cliente.getEmail());
		cli.setCpf(cliente.getCpf());
		cli.setCep(cliente.getCep());
		cli.setNumero(cliente.getNumero());
		cli.setTelefone(cliente.getTelefone());
		cli.setLogradouro(cliente.getLogradouro());
		cli.setComplemento(cliente.getComplemento());
		cli.setBairro(cliente.getBairro());
		cli.setCidade(cliente.getCidade());
		cli.setEstado(cliente.getEstado());
		cli.setPerfil(cliente.getPerfil());
		cli.setSenha(cliente.getSenha());
		return new ClienteDTO(repo.save(cliente));
	}
	
	public void excluirCliente(Long idcliente) {
		repo.deleteById(idcliente);
	}
	

}
