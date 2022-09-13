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
import com.pet.services.TipoProdutoService;
import com.pet.services.dto.TipoProdutoDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class TipoProdutoControllerTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private TipoProdutoDTO tipoProdutoExistente;
	private TipoProdutoDTO tipoProdutoNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TipoProdutoService service;      
	
	@Autowired   
	private ObjectMapper objectMapper;   
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		tipoProdutoNovo = new TipoProdutoDTO();
		tipoProdutoExistente = new TipoProdutoDTO("Racao");
		tipoProdutoExistente.setId(1L);
		
		Mockito.when(service.consultarTipoProdutoPorId(idExistente)).thenReturn(tipoProdutoExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(tipoProdutoNovo);
		Mockito.when(service.alterarTipoProduto(eq(idExistente), any())).thenReturn(tipoProdutoExistente); 
		Mockito.when(service.alterarTipoProduto(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirTipoProduto(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarTipoProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/tipoproduto/{idtipoproduto}", idExistente)
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
		String jsonBody = objectMapper.writeValueAsString(tipoProdutoNovo);
		ResultActions result = mockMvc.perform(post("/tipoproduto")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tipoProdutoExistente);
		ResultActions result = mockMvc.perform(put("/tipoproduto/{idtipoproduto}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarTipoProduto()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/tipoproduto"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirTipoProdutoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/tipoproduto/{idtipoproduto}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}

}
