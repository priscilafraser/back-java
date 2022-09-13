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
import com.pet.entidades.TipoServico;
import com.pet.services.ServicoService;
import com.pet.services.dto.ServicoDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class ServicoControllerTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	private ServicoDTO servicoExistente;
	private ServicoDTO servicoNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ServicoService service;      
	
	@Autowired   
	private ObjectMapper objectMapper;   
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		servicoNovo = new ServicoDTO();
		servicoExistente = new ServicoDTO("Racao", new TipoServico(), new Categoria());
		servicoExistente.setId(1L);
		
		Mockito.when(service.consultarServicoPorId(idExistente)).thenReturn(servicoExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(servicoNovo);
		Mockito.when(service.alterarServico(eq(idExistente), any())).thenReturn(servicoExistente); 
		Mockito.when(service.alterarServico(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirServico(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarServicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/servico/{idservico}", idExistente)
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
		String jsonBody = objectMapper.writeValueAsString(servicoNovo);
		ResultActions result = mockMvc.perform(post("/servico")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   
							.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(servicoExistente);
		ResultActions result = mockMvc.perform(put("/servico/{idservico}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarServico()).thenReturn(new ArrayList<>());

		ResultActions result = mockMvc.perform(get("/servico"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirServicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/servico/{idservico}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}

}
