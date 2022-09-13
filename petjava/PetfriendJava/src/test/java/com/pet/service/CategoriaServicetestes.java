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
import com.pet.repository.CategoriaRepository;
import com.pet.services.CategoriaService;
import com.pet.services.dto.CategoriaDTO;

@ExtendWith(SpringExtension.class)
public class CategoriaServicetestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private Categoria categoriaInvalida;
	private Categoria categoriaValida;
	private Categoria categoria;
	private List<Categoria> categorias;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		categoriaInvalida = new Categoria("Selvagem");  //colocar validacao, perguntar do prof
		categoriaValida = new Categoria("gato");
		categoria = new Categoria("cachorro");
		categorias = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(categoriaInvalida);
		Mockito.when(repository.save(categoriaValida)).thenReturn(categoriaValida);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Categoria()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(categorias);
		Mockito.when(repository.save(categoria)).thenReturn(categoria);   //dizendo o que deve fazer se fosse no repositorio real 
	}
	

	
	@InjectMocks
	CategoriaService service;
	
	@Mock
	CategoriaRepository repository;
	
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(categoriaInvalida));
		Mockito.verify(repository).save(categoriaInvalida);
	}
	
	@Test
	public void retornaCategoriaDTOQuandoSalvarComSucesso() {
		CategoriaDTO categoriaDTO = service.salvar(categoriaValida);
		Assertions.assertNotNull(categoriaDTO);
		Mockito.verify(repository).save(categoriaValida);
	}
	
	@Test
	public void retornaCategoriaQuandoIdExistente() {
		CategoriaDTO ct = service.consultarCategoriaPorId(idExistente);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarCategoriaPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirCategoria(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirCategoria(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeCategorias() {
		List<CategoriaDTO> ct = service.consultarCategoria();
		Assertions.assertNotNull(ct);     //se nao for nula
		Mockito.verify(repository).findAll();      //verificndo do repository o findall
	}
	
	@Test
	public void retornaCategoriaQuandoAlteradaComSucesso() {
		CategoriaDTO ct = service.alterarCategoria(idExistente, categoria);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository).save(categoria);    //simulacao do metodo save
	}
	
}
