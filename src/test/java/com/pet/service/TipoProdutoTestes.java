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

import com.pet.entidades.TipoProduto;
import com.pet.repository.TipoProdutoRepository;
import com.pet.services.TipoProdutoService;
import com.pet.services.dto.TipoProdutoDTO;



@ExtendWith(SpringExtension.class)
public class TipoProdutoTestes {
		
		private Long idExistente;
		private Long idNaoExistente;
		private TipoProduto tipoprodutoInvalido;
		private TipoProduto tipoprodutoValido;
		private TipoProduto tipoproduto;
		private List<TipoProduto> tipoprodutos;
		
		@BeforeEach
		void setup() {
			idExistente = 1l;
			idNaoExistente = 1000l;
			tipoprodutoInvalido = new TipoProduto("Selvagem");  
			tipoprodutoValido = new TipoProduto("racao");
			tipoproduto = new TipoProduto("acessorio");
			tipoprodutos = new ArrayList();
			
			Mockito.doThrow(IllegalArgumentException.class).when(repository).save(tipoprodutoInvalido);
			Mockito.when(repository.save(tipoprodutoValido)).thenReturn(tipoprodutoValido);
			
			Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new TipoProduto()));
			Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
			
			Mockito.doNothing().when(repository).deleteById(idExistente);
			Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
			
			Mockito.when(repository.findAll()).thenReturn(tipoprodutos);
			Mockito.when(repository.save(tipoproduto)).thenReturn(tipoproduto);    
		}
		

		
		@InjectMocks
		TipoProdutoService service;
		
		@Mock
		TipoProdutoRepository repository;
		
		
		@Test
		public void retornaExcecaoQuandoSalvarSemSucesso() {
			Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(tipoprodutoInvalido));
			Mockito.verify(repository).save(tipoprodutoInvalido);
		}
		
		@Test
		public void retornaCategoriaDTOQuandoSalvarComSucesso() {
			TipoProdutoDTO tipoprodutoDTO = service.salvar(tipoprodutoValido);
			Assertions.assertNotNull(tipoprodutoDTO);
			Mockito.verify(repository).save(tipoprodutoValido);
		}
		
		@Test
		public void retornaCategoriaQuandoIdExistente() {
			TipoProdutoDTO tp = service.consultarTipoProdutoPorId(idExistente);
			Assertions.assertNotNull(tp);
			Mockito.verify(repository).findById(idExistente);
		}
		
		@Test
		public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
			Assertions.assertThrows(EntityNotFoundException.class, ()-> {
				service.consultarTipoProdutoPorId(idNaoExistente);
			});
			Mockito.verify(repository).findById(idNaoExistente);
			
		}
		
		@Test
		public void retornaNadaAoExcluirIdExistente() {
			Assertions.assertDoesNotThrow(()-> {
				service.excluirTipoProduto(idExistente);
			});
			Mockito.verify(repository).deleteById(idExistente);
		}
		
		@Test
		public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
			Assertions.assertThrows(EntityNotFoundException.class, ()-> {
				service.excluirTipoProduto(idNaoExistente);
			});
			Mockito.verify(repository).deleteById(idNaoExistente);
			
		}
		
		@Test
		public void retornaListaDeTipoDeProdutos() {
			List<TipoProdutoDTO> tp = service.consultarTipoProduto();
			Assertions.assertNotNull(tp);     
			Mockito.verify(repository).findAll();     
		}
		
		@Test
		public void retornaCategoriaQuandoAlteradaComSucesso() {
			TipoProdutoDTO tp = service.alterarTipoProduto(idExistente, tipoproduto);
			Assertions.assertNotNull(tp);
			Mockito.verify(repository).save(tipoproduto);    
		}

}

