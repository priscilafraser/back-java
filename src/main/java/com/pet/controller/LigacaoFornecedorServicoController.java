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

import com.pet.entidades.LigacaoFornecedorServico;
import com.pet.services.LigacaoFornecedorServicoService;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;
import com.pet.services.dto.LigacaoFornecedorServicoDTO;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LigacaoFornecedorServicoController {

	@Autowired
	LigacaoFornecedorServicoService service;
	
	@GetMapping("/fornecedor-servico")
	public ResponseEntity<List<LigacaoFornecedorServicoDTO>> getLigacaoFornecedorServico() {
		List<LigacaoFornecedorServicoDTO> ligacaoFornecedorServico = service.consultarLigacaoFornecedorServico();
		return ResponseEntity.status(HttpStatus.OK).body(ligacaoFornecedorServico);
	}
	
	
	@GetMapping("/fornecedor-servico/{idligacaofornecedorservico}")
	public ResponseEntity<LigacaoFornecedorServicoDTO> getLigacaoFornecedorServicoById(@PathVariable("idligacaofornecedorservico") Long idligacaofornecedorservico) {
		return ResponseEntity.ok(service.consultarLigacaoFornecedorServicoPorId(idligacaofornecedorservico));
	}
	

	@PostMapping("/fornecedor-servico")
	public ResponseEntity<LigacaoFornecedorServicoDTO> salvarLigacaoFornecedorServico(@RequestBody LigacaoFornecedorServico ligacaofornecedorservico) {
		LigacaoFornecedorServicoDTO ct = service.salvar(ligacaofornecedorservico);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	
	@PutMapping("/fornecedor-servico/{idligacaofornecedorservico}")
	public ResponseEntity<LigacaoFornecedorServicoDTO> alteraLigacaoFornecedorServico(@PathVariable("idligacaofornecedorservico") Long idligacaofornecedorservico, @RequestBody LigacaoFornecedorServico ligacaofornecedorservico) {
		return ResponseEntity.ok(service.alterarLigacaoFornecedorServico(idligacaofornecedorservico, ligacaofornecedorservico));
	}

	@DeleteMapping("/fornecedor-servico/{idligacaofornecedorservico}")
	public ResponseEntity<Void> deleteLigacaoFornecedorServico(@PathVariable("idligacaofornecedorservico") Long idligacaofornecedorservico) {
		service.excluirLigacaoFornecedorServico(idligacaofornecedorservico);
		return ResponseEntity.noContent().build();
	}
	
	
	////////////////PERSONALIZADO
	@GetMapping("servicosFornecedor/{idfornecedor}")
	public ResponseEntity<List<LigacaoFornecedorServicoDTO>> consultaServicosPeloIdFornecedor(@PathVariable("idfornecedor") Long idfornecedor) {
	return ResponseEntity.ok(service.consultarServicosPorFornecedor(idfornecedor));
	}

}
