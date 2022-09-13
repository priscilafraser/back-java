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

import com.pet.entidades.TipoServico;
import com.pet.repository.TipoServicoRepository;
import com.pet.services.TipoServicoService;
import com.pet.services.dto.TipoServicoDTO;

@ExtendWith(SpringExtension.class)
public class TipoServicoServiceTestes {

	private Long idExistente;
	private Long idNaoExistente;
	private TipoServico tiposervicoInvalido;
	private TipoServico tiposervicoValido;
	private TipoServico tiposervico;
	private List<TipoServico> tiposervicos;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		tiposervicoInvalido = new TipoServico("adestramento");
		tiposervicoValido = new TipoServico("banho e tosa");
		tiposervico = new TipoServico("passeador");
		tiposervicos = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(tiposervicoInvalido);
		Mockito.when(repository.save(tiposervicoValido)).thenReturn(tiposervicoValido);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new TipoServico()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(tiposervicos);
		Mockito.when(repository.save(tiposervico)).thenReturn(tiposervico); 
	}
	

	
	@InjectMocks
	TipoServicoService service;
	
	@Mock
	TipoServicoRepository repository;
	
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(tiposervicoInvalido));
		Mockito.verify(repository).save(tiposervicoInvalido);
	}
	
	@Test
	public void retornaTipoServicoDTOQuandoSalvarComSucesso() {
		TipoServicoDTO tiposervicoDTO = service.salvar(tiposervicoValido);
		Assertions.assertNotNull(tiposervicoDTO);
		Mockito.verify(repository).save(tiposervicoValido);
	}
	
	@Test
	public void retornaTipoServicoQuandoIdExistente() {
		TipoServicoDTO ts = service.consultarTipoServicoPorId(idExistente);
		Assertions.assertNotNull(ts);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarTipoServicoPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirTipoServico(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirTipoServico(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeTipoDeServicos() {
		List<TipoServicoDTO> ts = service.consultarTipoServico();
		Assertions.assertNotNull(ts);
		Mockito.verify(repository).findAll();
	}
	
	@Test
	public void retornaTipoDeServicoQuandoAlteradaComSucesso() {
		TipoServicoDTO ts = service.alterarTipoServico(idExistente, tiposervico);
		Assertions.assertNotNull(ts);
		Mockito.verify(repository).save(tiposervico);
	}
}
