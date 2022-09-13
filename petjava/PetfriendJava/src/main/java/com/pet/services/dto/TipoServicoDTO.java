package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.TipoServico;

public class TipoServicoDTO {
	
	private Long id;
	private String servico;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	
	public TipoServicoDTO(String servico) {
		this.servico = servico;
	}
	
	public TipoServicoDTO() {
		
	}
	
	public TipoServicoDTO(TipoServico tiposervico) {
		this.id = tiposervico.getId();
		this.servico = tiposervico.getServico();
		
	}
	
	public static List<TipoServicoDTO> converteParaDTO(List<TipoServico> tiposervicos) {
		List<TipoServicoDTO> tiposervicosDTO = new ArrayList<>();
		for(TipoServico ts: tiposervicos) {
			tiposervicosDTO.add(new TipoServicoDTO(ts));
		}
		return tiposervicosDTO;
	}
	
}
