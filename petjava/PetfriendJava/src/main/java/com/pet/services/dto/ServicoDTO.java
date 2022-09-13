package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.Servico;
import com.pet.entidades.TipoServico;

public class ServicoDTO {
	
	private Long id;
	private String servicos;
	private TipoServico tipoServico;
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServicos() {
		return servicos;
	}
	public void setServicos(String servicos) {
		this.servicos = servicos;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public ServicoDTO(String servicos, TipoServico tipoServico, Categoria categoria) {
		this.servicos = servicos;
		this.tipoServico = tipoServico;
		this.categoria = categoria;
	}
	
	public ServicoDTO() {
		
	}
	
	public ServicoDTO(Servico servico) {
		this.id = servico.getId();
		this.servicos = servico.getServicos();
		this.tipoServico = servico.getTipoServico();
		this.categoria = servico.getCategoria();
		
	}
	
	public static List<ServicoDTO> converteParaDTO(List<Servico> servicos) {
		List<ServicoDTO> servicosDTO = new ArrayList<>();
		for(Servico sv: servicos) {
			servicosDTO.add(new ServicoDTO(sv));
		}
		return servicosDTO;
	}
	
	
	
}
