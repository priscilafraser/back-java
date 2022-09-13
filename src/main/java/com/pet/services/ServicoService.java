package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.Servico;
import com.pet.repository.ServicoRepository;
import com.pet.services.dto.ServicoDTO;

@Service
public class ServicoService {
	
	@Autowired
	ServicoRepository repo;
	
	public ServicoDTO salvar(Servico servico) {
		Servico sv = repo.save(servico);
		ServicoDTO servicoDTO = new ServicoDTO(sv); 
		return servicoDTO;
	}
	
	public List<ServicoDTO> consultarServico() {
		List<Servico> servico = repo.findAll();
		List<ServicoDTO> servicoDTO = new ArrayList();
		for(Servico sv: servico) {
			servicoDTO.add(new ServicoDTO(sv));
		}
		return servicoDTO;	
	}
	
	public ServicoDTO consultarServicoPorId(Long idservico) {
		Optional<Servico> opservico = repo.findById(idservico);
		Servico sv = opservico.orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));
		return new ServicoDTO(sv);	
	}
	
	public ServicoDTO alterarServico(Long idservico, Servico servico) {
		ServicoDTO sv = consultarServicoPorId(idservico);
		sv.setServicos(servico.getServicos());
		sv.setTipoServico(servico.getTipoServico());
		sv.setCategoria(servico.getCategoria());
		return new ServicoDTO(repo.save(servico));
	}
	
	public void excluirServico(Long idservico) {
		repo.deleteById(idservico);
	}
}
