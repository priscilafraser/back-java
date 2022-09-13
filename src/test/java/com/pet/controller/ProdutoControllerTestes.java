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
import com.pet.entidades.TipoProduto;
import com.pet.services.ProdutoService;
import com.pet.services.dto.ProdutoDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class ProdutoControllerTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private ProdutoDTO produtoExistente;
	private ProdutoDTO produtoNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;      
	
	@Autowired   
	private ObjectMapper objectMapper;   
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		produtoNovo = new ProdutoDTO();
		produtoExistente = new ProdutoDTO("Racao", new TipoProduto(), new Categoria());
		produtoExistente.setId(1L);
		
		Mockito.when(service.consultarProdutoPorId(idExistente)).thenReturn(produtoExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(produtoNovo);
		Mockito.when(service.alterarProduto(eq(idExistente), any())).thenReturn(produtoExistente); 
		Mockito.when(service.alterarProduto(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirProduto(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/produtos/{idproduto}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
//	@Test
//	public void deveRetornar404QuandoConsultaIdNaoExistente() throws Exception {
//		Mockito.when(service.consultarCategoriaPorId(idNaoExistente)).thenThrow(EntityNotFoundException.class);
//		ResultActions result = mockMvc.perform(get("/categoria/{idcategoria}", idNaoExistente)
//							   .accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isNotFound());
//	}
	
	@Test
	public void deveRetornar204SalvarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(produtoNovo);
		ResultActions result = mockMvc.perform(post("/produtos")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(produtoExistente);
		ResultActions result = mockMvc.perform(put("/produtos/{idproduto}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarProduto()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/produtos"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/produtos/{idproduto}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}

}
