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
import com.pet.entidades.LigacaoFornecedorProduto;
import com.pet.entidades.TipoProduto;
import com.pet.repository.LigacaoFornecedorProdutoRepository;
import com.pet.services.LigacaoFornecedorProdutoService;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;

@ExtendWith(SpringExtension.class)
public class LigacaoFornecedorProdutoTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private LigacaoFornecedorProduto ligacaoFornecedorProdutoInvalida;
	private LigacaoFornecedorProduto ligacaoFornecedorProdutoValida;
	private LigacaoFornecedorProduto ligacaoFornecedorProduto;
	private List<LigacaoFornecedorProduto> ligacaoFornecedoresProdutos;
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		ligacaoFornecedorProdutoInvalida = new LigacaoFornecedorProduto(5, "foto", new Categoria(), "eu", new Fornecedor(), new TipoProduto());
		ligacaoFornecedorProdutoValida = new LigacaoFornecedorProduto(5, "foto", new Categoria(), "eu", new Fornecedor(), new TipoProduto());
		ligacaoFornecedorProduto = new LigacaoFornecedorProduto(5,"foto", new Categoria(), "eu", new Fornecedor(), new TipoProduto());
		ligacaoFornecedoresProdutos = new ArrayList();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(ligacaoFornecedorProdutoInvalida);
		Mockito.when(repository.save(ligacaoFornecedorProdutoValida)).thenReturn(ligacaoFornecedorProdutoValida);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new LigacaoFornecedorProduto()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(ligacaoFornecedoresProdutos);
		Mockito.when(repository.save(ligacaoFornecedorProduto)).thenReturn(ligacaoFornecedorProduto); 
		
	}
	

	
	@InjectMocks
	LigacaoFornecedorProdutoService service;
	
	@Mock
	LigacaoFornecedorProdutoRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(ligacaoFornecedorProdutoInvalida));
		Mockito.verify(repository).save(ligacaoFornecedorProdutoInvalida);
	}
	
	@Test
	public void retornaLigacaoFornecedorProdutoDTOQuandoSalvarComSucesso() {
		LigacaoFornecedorProdutoDTO ligacaoFornecedorProdutoDTO = service.salvar(ligacaoFornecedorProdutoValida);
		Assertions.assertNotNull(ligacaoFornecedorProdutoDTO);
		Mockito.verify(repository).save(ligacaoFornecedorProdutoValida);
	}
	
	
	@Test
	public void retornaLigacaoFornecedorProdutoQuandoIdExistente() {
		LigacaoFornecedorProdutoDTO ligforprod = service.consultarLigacaoFornecedorProdutoPorId(idExistente);
		Assertions.assertNotNull(ligforprod);
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarLigacaoFornecedorProdutoPorId(idNaoExistente);
		});
		Mockito.verify(repository).findById(idNaoExistente);
		
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirLigacaoFornecedorProduto(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirLigacaoFornecedorProduto(idNaoExistente);
		});
		Mockito.verify(repository).deleteById(idNaoExistente);
		
	}
	
	@Test
	public void retornaListaDeLigacaoFornecedorProduto() {
		List<LigacaoFornecedorProdutoDTO> ligforprod = service.consultarLigacaoFornecedorProduto();
		Assertions.assertNotNull(ligforprod);
		Mockito.verify(repository).findAll();
	}
	
	@Test
	public void retornaLigacaoFornecedorProdutoQuandoAlteradaComSucesso() {
		LigacaoFornecedorProdutoDTO ligforprod = service.alterarLigacaoFornecedorProduto(idExistente, ligacaoFornecedorProduto);
		Assertions.assertNotNull(ligforprod);
		Mockito.verify(repository).save(ligacaoFornecedorProduto);
	}
	
	
	

}
