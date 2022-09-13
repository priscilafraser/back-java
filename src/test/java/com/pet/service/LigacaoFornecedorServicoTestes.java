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
import com.pet.entidades.Fornecedor;
import com.pet.entidades.LigacaoFornecedorServico;
import com.pet.entidades.TipoServico;
import com.pet.repository.LigacaoFornecedorServicoRepository;
import com.pet.services.LigacaoFornecedorServicoService;
import com.pet.services.dto.LigacaoFornecedorServicoDTO;

@ExtendWith(SpringExtension.class)
public class LigacaoFornecedorServicoTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private LigacaoFornecedorServico ligacaoFornecedorServicoInvalida;
	private LigacaoFornecedorServico ligacaoFornecedorServicoValida;
	private LigacaoFornecedorServico ligacaoFornecedorServico;
	private List<LigacaoFornecedorServico> ligacaoFornecedoresServicos;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		ligacaoFornecedorServicoInvalida = new LigacaoFornecedorServico(5, "ai", "13:00", "18:00", new Fornecedor(), new TipoServico(), new Categoria());
		ligacaoFornecedorServicoValida = new LigacaoFornecedorServico(5, "ai", "13:00", "18:00", new Fornecedor(), new TipoServico(), new Categoria());
		ligacaoFornecedorServico = new LigacaoFornecedorServico(5, "ai", "13:00", "18:00", new Fornecedor(), new TipoServico(), new Categoria());
		ligacaoFornecedoresServicos = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(ligacaoFornecedorServicoInvalida);
		Mockito.when(repository.save(ligacaoFornecedorServicoValida)).thenReturn(ligacaoFornecedorServicoValida);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new LigacaoFornecedorServico()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(ligacaoFornecedoresServicos);
		Mockito.when(repository.save(ligacaoFornecedorServico)).thenReturn(ligacaoFornecedorServico); 
		
	}
	

	
	@InjectMocks
	LigacaoFornecedorServicoService service;
	
	@Mock
	LigacaoFornecedorServicoRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(ligacaoFornecedorServicoInvalida));
		Mockito.verify(repository).save(ligacaoFornecedorServicoInvalida);
	}
	
	@Test
	public void retornaLigacaoFornecedorServicoDTOQuandoSalvarComSucesso() {
		LigacaoFornecedorServicoDTO ligacaoFornecedorServicoDTO = service.salvar(ligacaoFornecedorServicoValida);
		Assertions.assertNotNull(ligacaoFornecedorServicoDTO);
		Mockito.verify(repository).save(ligacaoFornecedorServicoValida);
	}
	
	
	@Test
	public void retornaLigacaoFornecedorServicoQuandoIdExistente() {
		LigacaoFornecedorServicoDTO ligforserv = service.consultarLigacaoFornecedorServicoPorId(idExistente);
		Assertions.assertNotNull(ligforserv);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarLigacaoFornecedorServicoPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirLigacaoFornecedorServico(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirLigacaoFornecedorServico(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeLigacaoFornecedorServico() {
		List<LigacaoFornecedorServicoDTO> ligforserv = service.consultarLigacaoFornecedorServico();
		Assertions.assertNotNull(ligforserv);
		Mockito.verify(repository).findAll();
	}
	
	@Test
	public void retornaLigacaoFornecedorServicoQuandoAlteradaComSucesso() {
		LigacaoFornecedorServicoDTO ligforserv = service.alterarLigacaoFornecedorServico(idExistente, ligacaoFornecedorServico);
		Assertions.assertNotNull(ligforserv);
		Mockito.verify(repository).save(ligacaoFornecedorServico);
	}
}
