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

import com.pet.entidades.Fornecedor;
import com.pet.repository.FornecedorRepository;
import com.pet.services.FornecedorService;
import com.pet.services.dto.FornecedorDTO;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class FornecedorController {
	
	
	@Autowired
	FornecedorRepository repo;
	
	@Autowired
	FornecedorService service;
	
	@GetMapping("/fornecedor")
	public ResponseEntity<List<FornecedorDTO>> getFornecedor() {
		List<FornecedorDTO> fornecedores = service.consultarFornecedor();
		return ResponseEntity.status(HttpStatus.OK).body(fornecedores);
	}
	
	
	@GetMapping("/fornecedor/{idfornecedor}")
	public ResponseEntity<FornecedorDTO> getFornecedorById(@PathVariable("idfornecedor") Long idfornecedor) {
		return ResponseEntity.ok(service.consultarFornecedorPorId(idfornecedor));
	}
	

	@PostMapping("/fornecedor")  //feito
	public ResponseEntity<FornecedorDTO> salvarFornecedor(@RequestBody Fornecedor fornecedor) {
		FornecedorDTO fr = service.salvar(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fr);
	}
	
	
	@PutMapping("fornecedor/{idfornecedor}")
	public ResponseEntity<FornecedorDTO> alterarFornecedor(@PathVariable("idfornecedor") Long idfornecedor, @RequestBody Fornecedor fornecedor) {
		return ResponseEntity.ok(service.alterarFornecedor(idfornecedor, fornecedor));
	}

	@DeleteMapping("fornecedor/{idfornecedor}")
	public ResponseEntity<Void> deleteFornecedor(@PathVariable("idfornecedor") Long idfornecedor) {
		service.excluirFornecedor(idfornecedor);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
}
