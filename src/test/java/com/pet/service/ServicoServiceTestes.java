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

import com.pet.entidades.Categoria;
import com.pet.entidades.Servico;
import com.pet.entidades.TipoServico;
import com.pet.repository.CategoriaRepository;
import com.pet.repository.ServicoRepository;
import com.pet.services.CategoriaService;
import com.pet.services.ServicoService;
import com.pet.services.dto.CategoriaDTO;
import com.pet.services.dto.ServicoDTO;

@ExtendWith(SpringExtension.class)
public class ServicoServiceTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private Servico servicoInvalido;
	private Servico servicoValido;
	private Servico servico;
	private List<Servico> servicos;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		servicoInvalido = new Servico("adestramento", new TipoServico(), new Categoria());
		servicoValido = new Servico("banho e tosa", new TipoServico(), new Categoria());
		servico = new Servico("passeador", new TipoServico(), new Categoria());
		servicos = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(servicoInvalido);
		Mockito.when(repository.save(servicoValido)).thenReturn(servicoValido);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Servico()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(servicos);
		Mockito.when(repository.save(servico)).thenReturn(servico); 
	}
	
	@InjectMocks
	ServicoService service;
	
	@Mock
	ServicoRepository repository;
	
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(servicoInvalido));
		Mockito.verify(repository).save(servicoInvalido);
	}
	
	@Test
	public void retornaServicoDTOQuandoSalvarComSucesso() {
		ServicoDTO servicoDTO = service.salvar(servicoValido);
		Assertions.assertNotNull(servicoDTO);
		Mockito.verify(repository).save(servicoValido);
	}
	
	@Test
	public void retornaServicoQuandoIdExistente() {
		ServicoDTO sv = service.consultarServicoPorId(idExistente);
		Assertions.assertNotNull(sv);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarServicoPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirServico(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirServico(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeServicos() {
		List<ServicoDTO> sv = service.consultarServico();
		Assertions.assertNotNull(sv);
		Mockito.verify(repository).findAll();
	}
	
	@Test
	public void retornaServicoQuandoAlteradoComSucesso() {
		ServicoDTO sv = service.alterarServico(idExistente, servico);
		Assertions.assertNotNull(sv);
		Mockito.verify(repository).save(servico);
	}

}
