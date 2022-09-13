package com.pet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.entidades.Categoria;
import com.pet.entidades.Fornecedor;
import com.pet.entidades.TipoProduto;
import com.pet.services.LigacaoFornecedorProdutoService;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class LigacaoFornecedorProdutoControllerTestes {

	private Long idExistente;
	private Long idNaoExistente;
	private LigacaoFornecedorProdutoDTO ligforprodExistente;
	private LigacaoFornecedorProdutoDTO ligforprodNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LigacaoFornecedorProdutoService service;
	
	@Autowired   
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		ligforprodNovo = new LigacaoFornecedorProdutoDTO();
		ligforprodExistente = new LigacaoFornecedorProdutoDTO(30, "foto", "descreve", new Fornecedor(), new TipoProduto(), new Categoria());
		ligforprodExistente.setId(1L);		

		Mockito.when(service.consultarLigacaoFornecedorProdutoPorId(idExistente)).thenReturn(ligforprodExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarLigacaoFornecedorProdutoPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(ligforprodNovo);
		Mockito.when(service.alterarLigacaoFornecedorProduto(eq(idExistente), any())).thenReturn(ligforprodExistente); 
		Mockito.when(service.alterarLigacaoFornecedorProduto(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirLigacaoFornecedorProduto(idExistente);
	}
	
	@Test
	public void deveRetornarLigacaoFornecedorProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor-produto/{idligacaofornecedorproduto}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
//	@Test
//	public void deveRetornar404QuandoConsultaIdNaoExistente() throws Exception {
//		Mockito.when(service.consultarLigacaoFornecedorProdutoPorId(idNaoExistente)).thenThrow(EntityNotFoundException.class);
//		ResultActions result = mockMvc.perform(get("/fornecedor-produto/{idligacaofornecedorproduto}", idNaoExistente)
//							   .accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isNotFound());
//	}
	
	@Test
	public void deveRetornar204SalvarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(ligforprodNovo);
		ResultActions result = mockMvc.perform(post("/fornecedor-produto")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(ligforprodExistente);
		ResultActions result = mockMvc.perform(put("/fornecedor-produto/{idligacaofornecedorproduto}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarLigacaoFornecedorProduto()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/fornecedor-produto"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirLigacaoFornecedorProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/fornecedor-produto/{idligacaofornecedorproduto}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	
	
}
