package com.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.entidades.Categoria;
import com.pet.services.CategoriaService;
import com.pet.services.dto.CategoriaDTO;

@RestController    
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class CategoriaController {
	

	@Autowired
	CategoriaService service;
	
	@GetMapping("/categoria")
	public ResponseEntity<List<CategoriaDTO>> getCategoria() {
		List<CategoriaDTO> categorias = service.consultarCategoria();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
	
	
	@GetMapping("/categoria/{idcategoria}")
	public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("idcategoria") Long idcategoria) {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultarCategoriaPorId(idcategoria));
	}
	

	@PostMapping("/categoria")
	public ResponseEntity<CategoriaDTO> salvarCategoria(@RequestBody Categoria categoria) {
		CategoriaDTO ct = service.salvar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	
	@PutMapping("categoria/{idcategoria}")
	public ResponseEntity<CategoriaDTO> alteraCategoria(@PathVariable("idcategoria") Long idcategoria, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(service.alterarCategoria(idcategoria, categoria));
	}

	@DeleteMapping("categoria/{idcategoria}")
	public ResponseEntity<Void> deleteCategoria(@PathVariable("idcategoria") Long idcategoria) {
		service.excluirCategoria(idcategoria);
		return ResponseEntity.noContent().build();
	}
	
}
