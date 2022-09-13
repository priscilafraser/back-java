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

import com.pet.entidades.LigacaoFornecedorProduto;
import com.pet.services.LigacaoFornecedorProdutoService;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LigacaoFornecedorProdutoController {
	
	@Autowired
	LigacaoFornecedorProdutoService service;
	
	@GetMapping("/fornecedor-produto")
	public ResponseEntity<List<LigacaoFornecedorProdutoDTO>> getLigacaoFornecedorProduto() {
		List<LigacaoFornecedorProdutoDTO> ligacaoFornecedorProduto = service.consultarLigacaoFornecedorProduto();
		return ResponseEntity.status(HttpStatus.OK).body(ligacaoFornecedorProduto);
	}
	
	
	@GetMapping("/fornecedor-produto/{idligacaofornecedorproduto}")
	public ResponseEntity<LigacaoFornecedorProdutoDTO> getLigacaoFornecedorProdutoById(@PathVariable("idligacaofornecedorproduto") Long idligacaofornecedorproduto) {
		return ResponseEntity.ok(service.consultarLigacaoFornecedorProdutoPorId(idligacaofornecedorproduto));
	}
	

	@PostMapping("/fornecedor-produto")
	public ResponseEntity<LigacaoFornecedorProdutoDTO> salvarLigacaoFornecedorProduto(@RequestBody LigacaoFornecedorProduto ligacaofornecedorproduto) {
		LigacaoFornecedorProdutoDTO ct = service.salvar(ligacaofornecedorproduto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	
	@PutMapping("fornecedor-produto/{idligacaofornecedorproduto}")
	public ResponseEntity<LigacaoFornecedorProdutoDTO> alteraLigacaoFornecedorProduto(@PathVariable("idligacaofornecedorproduto") Long idligacaofornecedorproduto, @RequestBody LigacaoFornecedorProduto ligacaofornecedorproduto) {
		return ResponseEntity.ok(service.alterarLigacaoFornecedorProduto(idligacaofornecedorproduto, ligacaofornecedorproduto));
	}

	@DeleteMapping("fornecedor-produto/{idligacaofornecedorproduto}")
	public ResponseEntity<Void> deleteLigacaoFornecedorProduto(@PathVariable("idligacaofornecedorproduto") Long idligacaofornecedorproduto) {
		service.excluirLigacaoFornecedorProduto(idligacaofornecedorproduto);
		return ResponseEntity.noContent().build();
	}
	
	////////////////PERSONALIZADO
	@GetMapping("produtosFornecedor/{idfornecedor}")
	public ResponseEntity<List<LigacaoFornecedorProdutoDTO>> consultaProdutosPeloIdFornecedor(@PathVariable("idfornecedor") Long idfornecedor) {
		return ResponseEntity.ok(service.consultarProdutosPorFornecedor(idfornecedor));
	}


}
