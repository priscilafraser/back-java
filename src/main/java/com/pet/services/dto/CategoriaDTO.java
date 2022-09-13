package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;

public class CategoriaDTO {
	
	private Long id;
	
	private String categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public CategoriaDTO(String categoria) {
		this.categoria = categoria;
	}

	public CategoriaDTO() {

	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.categoria = categoria.getCategoria();
		
	}
	
	public static List<CategoriaDTO> converteParaDTO(List<Categoria> categorias) {
		List<CategoriaDTO> categoriasDTO = new ArrayList<>();
		for(Categoria ct: categorias) {
			categoriasDTO.add(new CategoriaDTO(ct));
		}
		return categoriasDTO;
	}
	
	//.refazendo

}
