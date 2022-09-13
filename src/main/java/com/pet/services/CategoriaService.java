package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.Categoria;
import com.pet.repository.CategoriaRepository;
import com.pet.services.dto.CategoriaDTO;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	public CategoriaDTO salvar(Categoria categoria) {
		Categoria ct = repo.save(categoria);
		CategoriaDTO categoriaDTO = new CategoriaDTO(ct); 
		return categoriaDTO;
	}
	
	public List<CategoriaDTO> consultarCategoria() {
		List<Categoria> categoria = repo.findAll();
		List<CategoriaDTO> categoriaDTO = new ArrayList();
		for(Categoria ct: categoria) {
			categoriaDTO.add(new CategoriaDTO(ct));    //recebe como construtor o ct
		}
		return categoriaDTO;	
	}
	
	public CategoriaDTO consultarCategoriaPorId(Long idcategoria) {
		Optional<Categoria> opcategoria = repo.findById(idcategoria);
		Categoria ct = opcategoria.orElseThrow(() -> new EntityNotFoundException("Categoria nao encontrado"));
		return new CategoriaDTO(ct);	
	}
	
	public CategoriaDTO alterarCategoria(Long idcategoria, Categoria categoria) {
		CategoriaDTO ct = consultarCategoriaPorId(idcategoria);
		ct.setCategoria(categoria.getCategoria());
		return new CategoriaDTO(repo.save(categoria));
	}
	
	public void excluirCategoria(Long idcategoria) {
		repo.deleteById(idcategoria);
	}
	
	
}
