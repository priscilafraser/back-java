package com.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.entidades.Categoria;
import com.pet.entidades.Produto;
import com.pet.repository.ProdutoRepository;
import com.pet.services.CategoriaService;
import com.pet.services.ProdutoService;
import com.pet.services.dto.CategoriaDTO;
import com.pet.services.dto.ProdutoDTO;



@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	
	@Autowired
	ProdutoService service;
	
	@GetMapping("/produtos")
	public ResponseEntity<List<ProdutoDTO>> getProdutos() {
		List<ProdutoDTO> produtos = service.consultarProduto();
		return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}
	
	
	@GetMapping("/produtos/{idproduto}")
	public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable("idproduto") Long idproduto) {
		return ResponseEntity.ok(service.consultarProdutoPorId(idproduto));
	}
	

	@PostMapping("/produtos")  
	public ResponseEntity<ProdutoDTO> salvarproduto(@RequestBody Produto produto) {
		ProdutoDTO pr = service.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(pr);
	}
	
	
	@PutMapping("/produtos/{idproduto}")
	public ResponseEntity<ProdutoDTO> alteraProduto(@PathVariable("idproduto") Long idproduto, @RequestBody Produto produto) {
		return ResponseEntity.ok(service.alterarProduto(idproduto, produto));
	}

	@DeleteMapping("/produtos/{idproduto}")
	public ResponseEntity<Void> deleteProduto(@PathVariable("idproduto") Long idproduto) {
		service.excluirProduto(idproduto);
		return ResponseEntity.noContent().build();
	}
	
	
	
}