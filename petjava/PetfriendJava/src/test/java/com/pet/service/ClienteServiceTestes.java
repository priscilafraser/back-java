package com.pet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pet.entidades.Cliente;
import com.pet.repository.ClienteRepository;
import com.pet.services.ClienteService;
import com.pet.services.dto.ClienteDTO;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private Cliente clienteInvalido;
	private Cliente clienteValido;
	private Cliente cliente;
	private List<Cliente> clientes;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		clienteInvalido = new Cliente("Pedro", "pedro@gmail.com", "666666666666", "999999999999999", 6, "666", "666", "Cond", "Ouro", "Maua", "am", "l", "123");  //colocar validacao, perguntar do prof
		clienteValido = new Cliente("Pedro", "pedro@gmail.com", "66666666666", "99999999", 6, "666", "666", "Cond", "Ouro", "Maua", "am", "l", "123");
		cliente = new Cliente("Pedro", "pedro@gmail.com", "66666666666", "99999999", 6, "666", "666", "Cond", "Ouro", "Maua", "am", "l", "123");
		clientes = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(clienteInvalido);
		Mockito.when(repository.save(clienteValido)).thenReturn(clienteValido);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Cliente()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(clientes);
		Mockito.when(repository.save(cliente)).thenReturn(cliente);
	}
	

	
	@InjectMocks
	ClienteService service;
	
	@Mock
	ClienteRepository repository;
	
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(clienteInvalido));
		Mockito.verify(repository).save(clienteInvalido);
	}
	
	@Test
	public void retornaClienteDTOQuandoSalvarComSucesso() {
		ClienteDTO clienteDTO = service.salvar(clienteValido);
		Assertions.assertNotNull(clienteDTO);
		Mockito.verify(repository).save(clienteValido);
	}
	
	@Test
	public void retornaClienteQuandoIdExistente() {
		ClienteDTO cli = service.consultarClientePorId(idExistente);
		Assertions.assertNotNull(cli);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarClientePorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirCliente(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirCliente(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeClientes() {
		List<ClienteDTO> cli = service.consultarCliente();
		Assertions.assertNotNull(cli);     
		Mockito.verify(repository).findAll();    
	}
	
	@Test
	public void retornaClienteQuandoAlteradaComSucesso() {
		ClienteDTO cli = service.alterarCliente(idExistente, cliente);
		Assertions.assertNotNull(cli);
		Mockito.verify(repository).save(cliente);
	}

}
