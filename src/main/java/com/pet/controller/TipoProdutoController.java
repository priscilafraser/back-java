package com.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pet.entidades.TipoProduto;
import com.pet.services.TipoProdutoService;
import com.pet.services.dto.TipoProdutoDTO;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TipoProdutoController {
	
	@Autowired
	TipoProdutoService service;
	
	
	@GetMapping("/tipoproduto")
	public ResponseEntity<List<TipoProdutoDTO>> getTipoProduto() {
		List<TipoProdutoDTO> tipoprodutos = service.consultarTipoProduto();
		return ResponseEntity.status(HttpStatus.OK).body(tipoprodutos);
	}
	
	
	@GetMapping("/tipoproduto/{idtipoproduto}")
	public ResponseEntity<TipoProdutoDTO> getTipoProdutoById(@PathVariable("idtipoproduto") Long idtipoproduto) {
		return ResponseEntity.ok(service.consultarTipoProdutoPorId(idtipoproduto));
	}
	

	@PostMapping("/tipoproduto")  //feito
	public ResponseEntity<TipoProdutoDTO> salvarTipoProduto(@RequestBody TipoProduto tipoproduto) {
		TipoProdutoDTO tp = service.salvar(tipoproduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tp);
	}
	
	
	@PutMapping("/tipoproduto/{idtipoproduto}")
	public ResponseEntity<TipoProdutoDTO> alteraTipoProduto(@PathVariable("idtipoproduto") Long idtipoproduto, @RequestBody TipoProduto tipoproduto) {
		return ResponseEntity.ok(service.alterarTipoProduto(idtipoproduto, tipoproduto));
	}

	@DeleteMapping("tipoproduto/{idtipoproduto}")
	public ResponseEntity<Void> deleteTipoProduto(@PathVariable("idtipoproduto") Long idtipoproduto) {
		service.excluirTipoProduto(idtipoproduto);
		return ResponseEntity.noContent().build();
	}

}
