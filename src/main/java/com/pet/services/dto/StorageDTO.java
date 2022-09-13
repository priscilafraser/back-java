package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Storage;

public class StorageDTO {
	
	private Long id;
	private String imagem;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public StorageDTO(String imagem) {
		this.imagem = imagem;
	}
	
	public StorageDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public StorageDTO(Storage storage) {
		this.id = storage.getId();
		this.imagem = storage.getImagem();
	}
	
	public static List<StorageDTO> converteParaDTO(List<Storage> imagens) {
		List<StorageDTO> storageDTO = new ArrayList<>();
		for(Storage sv: imagens) {
			storageDTO.add(new StorageDTO(sv));
		}
		return storageDTO;
	}
	
	
	
	
	
	
	

}
