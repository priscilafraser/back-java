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
import com.pet.services.ClienteService;
import com.pet.services.dto.ClienteDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class ClienteControllerTestes {

	private Long idExistente;
	private Long idNaoExistente;
	private ClienteDTO clienteExistente;
	private ClienteDTO clienteNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService service;      //para rodar um controller precisamos de Pessoa service
	
	@Autowired   
	private ObjectMapper objectMapper;   //ajuda a transformar objeto em string
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		clienteNovo = new ClienteDTO();
		clienteExistente = new ClienteDTO("Pedro", "pedro@gmail.com", "666666666666", "999999999999999", 6, "666", "666", "Cond", "Ouro", "Maua", "am", "l", "123");
		clienteExistente.setId(1L);
		
		Mockito.when(service.consultarClientePorId(idExistente)).thenReturn(clienteExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(clienteNovo);
		Mockito.when(service.alterarCliente(eq(idExistente), any())).thenReturn(clienteExistente); 
		Mockito.when(service.alterarCliente(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirCliente(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarClienteQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/cliente/{idcliente}", idExistente)
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
		String jsonBody = objectMapper.writeValueAsString(clienteNovo);
		ResultActions result = mockMvc.perform(post("/cliente")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   //formato de dado de entrada, do body do 73
							.accept(MediaType.APPLICATION_JSON));    //o formato que aceita como um todo
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(clienteExistente);
		ResultActions result = mockMvc.perform(put("/cliente/{idcliente}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarCliente()).thenReturn(new ArrayList<>());         //criar comportamento do mockito e depois executa o teste

		ResultActions result = mockMvc.perform(get("/cliente"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirClienteQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/cliente/{idcliente}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	
}
