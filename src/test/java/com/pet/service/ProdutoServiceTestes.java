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
import com.pet.entidades.Produto;
import com.pet.entidades.TipoProduto;
import com.pet.repository.ProdutoRepository;
import com.pet.services.ProdutoService;
import com.pet.services.dto.ProdutoDTO;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private Produto produtoInvalido;
	private Produto produtoValido;
	private Produto produto;
	private List<Produto> produtos;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		produtoInvalido = new Produto("racao",new TipoProduto(),new Categoria());  
		produtoValido = new Produto("gato",new TipoProduto(),new Categoria());
		produto = new Produto("racao",new TipoProduto(),new Categoria());
		produtos = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(produtoInvalido);
		Mockito.when(repository.save(produtoValido)).thenReturn(produtoValido);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Produto()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(produtos);
		Mockito.when(repository.save(produto)).thenReturn(produto);   
	}
	

	
	@InjectMocks
	ProdutoService service;
	
	@Mock
	ProdutoRepository repository;
	
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(produtoInvalido));
		Mockito.verify(repository).save(produtoInvalido);
	}
	
	@Test
	public void retornaProdutoDTOQuandoSalvarComSucesso() {
		ProdutoDTO produtoDTO = service.salvar(produtoValido);
		Assertions.assertNotNull(produtoDTO);
		Mockito.verify(repository).save(produtoValido);
	}
	
	@Test
	public void retornaProdutoQuandoIdExistente() {
		ProdutoDTO pr = service.consultarProdutoPorId(idExistente);
		Assertions.assertNotNull(pr);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarProdutoPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirProduto(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirProduto(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeProdutos() {
		List<ProdutoDTO> pr = service.consultarProduto();
		Assertions.assertNotNull(pr);   
		Mockito.verify(repository).findAll();   } 
	
	@Test
	public void retornaProdutoQuandoAlteradaComSucesso() {
		ProdutoDTO pr = service.alterarProduto(idExistente, produto);
		Assertions.assertNotNull(pr);
		Mockito.verify(repository).save(produto);    
	}
	
}
