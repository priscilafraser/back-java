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

import com.pet.entidades.Cliente;
import com.pet.services.ClienteService;
import com.pet.services.dto.ClienteDTO;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping("/cliente")
	public ResponseEntity<List<ClienteDTO>> getCliente() {
		List<ClienteDTO> clientes = service.consultarCliente();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	
	@GetMapping("/cliente/{idcliente}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable("idcliente") Long idcliente) {
		return ResponseEntity.ok(service.consultarClientePorId(idcliente));
	}
	

	@PostMapping("/cliente")  //feito
	public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody Cliente cliente) {
		ClienteDTO cli = service.salvar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cli);
	}
	
	
	@PutMapping("cliente/{idcliente}")
	public ResponseEntity<ClienteDTO> alteraCliente(@PathVariable("idcliente") Long idcliente, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(service.alterarCliente(idcliente, cliente));
	}

	@DeleteMapping("cliente/{idcliente}")
	public ResponseEntity<Void> deleteCliente(@PathVariable("idcliente") Long idcliente) {
		service.excluirCliente(idcliente);
		return ResponseEntity.noContent().build();
	}

}
