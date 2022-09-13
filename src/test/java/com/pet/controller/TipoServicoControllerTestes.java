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
import com.pet.services.TipoServicoService;
import com.pet.services.dto.TipoServicoDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class TipoServicoControllerTestes {
	private Long idExistente;
	private Long idNaoExistente;
	private TipoServicoDTO tiposervExistente;
	private TipoServicoDTO tiposervNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TipoServicoService service;
	
	@Autowired   
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		tiposervNovo = new TipoServicoDTO();
		tiposervExistente = new TipoServicoDTO("banho");
		tiposervExistente.setId(1L);
		
		Mockito.when(service.consultarTipoServicoPorId(idExistente)).thenReturn(tiposervExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(tiposervNovo);
		Mockito.when(service.alterarTipoServico(eq(idExistente), any())).thenReturn(tiposervExistente); 
		Mockito.when(service.alterarTipoServico(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirTipoServico(idExistente);
	
	}
	
	@Test
	public void deveRetornarTipoServicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/tiposervico/{idtiposervico}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
//	@Test
//	public void deveRetornar404QuandoConsultaIdNaoExistente() throws Exception {
//		Mockito.when(service.consultarTipoServicoPorId(idNaoExistente)).thenThrow(EntityNotFoundException.class);
//		ResultActions result = mockMvc.perform(get("/tiposervico/{idtiposervico}", idNaoExistente)
//							   .accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isNotFound());
//	}
	
	@Test
	public void deveRetornar204SalvarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tiposervNovo);
		ResultActions result = mockMvc.perform(post("/tiposervico")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tiposervExistente);
		ResultActions result = mockMvc.perform(put("/tiposervico/{idtiposervico}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarTipoServico()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/tiposervico"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirTipoSErvicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/tiposervico/{idtiposervico}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
}
