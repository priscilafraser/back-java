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
import com.pet.entidades.TipoServico;
import com.pet.services.LigacaoFornecedorServicoService;
import com.pet.services.dto.LigacaoFornecedorServicoDTO;

@SpringBootTest
@AutoConfigureMockMvc 
public class LigacaoFornecedorServicoControllerTestes {

	private Long idExistente;
	private Long idNaoExistente;
	private  LigacaoFornecedorServicoDTO pExistente;
	private  LigacaoFornecedorServicoDTO pNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  LigacaoFornecedorServicoService service;    
	
	@Autowired   
	private ObjectMapper objectMapper;  
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		pNovo = new LigacaoFornecedorServicoDTO();
		pExistente = new LigacaoFornecedorServicoDTO(10,"descreve",new Fornecedor(),new TipoServico(), new Categoria(), "8:00", "12:00");
		pExistente.setId(1L);
		
		Mockito.when(service.consultarLigacaoFornecedorServicoPorId(idExistente)).thenReturn(pExistente);
		//Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaPorId(idNaoExistente);
		

		Mockito.when(service.salvar(any())).thenReturn(pNovo);
		Mockito.when(service.alterarLigacaoFornecedorServico(eq(idExistente), any())).thenReturn(pExistente); 
		Mockito.when(service.alterarLigacaoFornecedorServico(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(service).excluirLigacaoFornecedorServico(idExistente);
	
	}
	
	
	@Test
	public void deveRetornarLigacaoFornecedorServicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor-servico/{idligacaofornecedorservico}", idExistente)
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
		String jsonBody = objectMapper.writeValueAsString(pNovo);
		ResultActions result = mockMvc.perform(post("/fornecedor-servico")
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON)   //formato de dado de entrada, do body do 73
							.accept(MediaType.APPLICATION_JSON));    //o formato que aceita como um todo
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void deveRetornarOkQuandoAlterarComSucesso() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(pExistente);
		ResultActions result = mockMvc.perform(put("/fornecedor-servico/{idligacaofornecedorservico}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}	
	
	@Test
	public void retornaListaQuandoConsultaTodosComSucesso() throws Exception {
		Mockito.when(service.consultarLigacaoFornecedorServico()).thenReturn(new ArrayList<>());         //criar comportamento do mockito e depois executa o teste

		ResultActions result = mockMvc.perform(get("/fornecedor-servico"));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveExcluirLigacaoFornecedorServicoQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(delete("/fornecedor-servico/{idligacaofornecedorservico}", idExistente)
							   .accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	

		
}
