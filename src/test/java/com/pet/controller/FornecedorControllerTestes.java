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
import com.pet.services.FornecedorService;
import com.pet.services.dto.FornecedorDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class FornecedorControllerTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private FornecedorDTO fornecedorExistente;
	private FornecedorDTO fornecedorNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FornecedorService service;      
	
	@Autowired   
	private ObjectMapper objectMapper;   
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		fornecedorNovo = new FornecedorDTO();
		fornecedorExistente = new FornecedorDTO("Pedro", "pedro@gmail.com", "666666666666", "999999999999999", "88888", "666", 8, "Cond", "Ouro", "Maua", "am", "l", "123");
		fornecedorExistente.setId(1L);
		
		Mockito.when(service.consultarFornecedorPorId(idExistente)).thenReturn(fornecedorExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(fornecedorNovo);
		Mockito.when(service.alterarFornecedor(eq(idExistente), any())).thenReturn(fornecedorExistente); 
		Mockito.when(service.alterarFornecedor(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirFornecedor(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarFornecedorQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor/{idfornecedor}", idExistente)
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
		String jsonBody = objectMapper.writeValueAsString(fornecedorNovo);
		ResultActions result = mockMvc.perform(post("/fornecedor")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(fornecedorExistente);
		ResultActions result = mockMvc.perform(put("/fornecedor/{idfornecedor}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarFornecedor()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/fornecedor"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirFornecedorQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/fornecedor/{idfornecedor}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}

}
