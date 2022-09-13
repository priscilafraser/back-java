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

import com.pet.entidades.TipoServico;
import com.pet.services.TipoServicoService;
import com.pet.services.dto.TipoServicoDTO;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TipoServicoController {
	
	@Autowired
	TipoServicoService service;
	
	@GetMapping("/tiposervico")
	public ResponseEntity<List<TipoServicoDTO>> getTipoServico() {
		List<TipoServicoDTO> tiposervicos = service.consultarTipoServico();
		return ResponseEntity.status(HttpStatus.OK).body(tiposervicos);
	}
	
	
	@GetMapping("/tiposervico/{idtiposervico}")
	public ResponseEntity<TipoServicoDTO> getTipoServicoById(@PathVariable("idtiposervico") Long idtiposervico) {
		return ResponseEntity.ok(service.consultarTipoServicoPorId(idtiposervico));
	}
	

	@PostMapping("/tiposervico")
	public ResponseEntity<TipoServicoDTO> salvarTipoServico(@RequestBody TipoServico tiposervico) {
		TipoServicoDTO ts = service.salvar(tiposervico);
		return ResponseEntity.status(HttpStatus.CREATED).body(ts);
	}
	
	
	@PutMapping("/tiposervico/{idtiposervico}")
	public ResponseEntity<TipoServicoDTO> alteraTipoServico(@PathVariable("idtiposervico") Long idtiposervico, @RequestBody TipoServico tiposervico) {
		return ResponseEntity.ok(service.alterarTipoServico(idtiposervico, tiposervico));
	}

	@DeleteMapping("/tiposervico/{idtiposervico}")
	public ResponseEntity<Void> deleteTipoServico(@PathVariable("idtiposervico") Long idtiposervico) {
		service.excluirTipoServico(idtiposervico);
		return ResponseEntity.noContent().build();
	}
}
