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

import com.pet.entidades.Servico;
import com.pet.services.ServicoService;
import com.pet.services.dto.ServicoDTO;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class ServicoController {

	@Autowired
	ServicoService service;
	
	@GetMapping("/servico")
	public ResponseEntity<List<ServicoDTO>> getServico() {
		List<ServicoDTO> servicos = service.consultarServico();
		return ResponseEntity.status(HttpStatus.OK).body(servicos);
	}
	
	
	@GetMapping("/servico/{idservico}")
	public ResponseEntity<ServicoDTO> getServicoById(@PathVariable("idservico") Long idservico) {
		return ResponseEntity.ok(service.consultarServicoPorId(idservico));
	}
	

	@PostMapping("/servico")
	public ResponseEntity<ServicoDTO> salvarServico(@RequestBody Servico servico) {
	logger.log(servico.toString());
		ServicoDTO sv = service.salvar(servico);
		return ResponseEntity.status(HttpStatus.CREATED).body(sv);
	}
	
	
	@PutMapping("/servico/{idservico}")
	public ResponseEntity<ServicoDTO> alteraServico(@PathVariable("idservico") Long idservico, @RequestBody Servico servico) {
		return ResponseEntity.ok(service.alterarServico(idservico, servico));
	}

	@DeleteMapping("/servico/{idservico}")
	public ResponseEntity<Void> deleteServico(@PathVariable("idservico") Long idservico) {
		service.excluirServico(idservico);
		return ResponseEntity.noContent().build();
	}
	
	
}
