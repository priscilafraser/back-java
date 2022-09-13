package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.TipoServico;
import com.pet.repository.TipoServicoRepository;
import com.pet.services.dto.TipoServicoDTO;

@Service
public class TipoServicoService {
	
	@Autowired
	TipoServicoRepository repo;
	
	public TipoServicoDTO salvar(TipoServico tiposervico) {
		TipoServico ts = repo.save(tiposervico);
		TipoServicoDTO tiposervicoDTO = new TipoServicoDTO(ts); 
		return tiposervicoDTO;
	}
	
	public List<TipoServicoDTO> consultarTipoServico() {
		List<TipoServico> tiposervico = repo.findAll();
		List<TipoServicoDTO> tiposervicoDTO = new ArrayList();
		for(TipoServico ts: tiposervico) {
			tiposervicoDTO.add(new TipoServicoDTO(ts));
		}
		return tiposervicoDTO;	
	}
	
	public TipoServicoDTO consultarTipoServicoPorId(Long idtiposervico) {
		Optional<TipoServico> optiposervico = repo.findById(idtiposervico);
		TipoServico ts = optiposervico.orElseThrow(() -> new EntityNotFoundException("Tipo de Serviço não encontrado"));
		return new TipoServicoDTO(ts);	
	}
	
	public TipoServicoDTO alterarTipoServico(Long idtiposervico, TipoServico tiposervico) {
		TipoServicoDTO ts = consultarTipoServicoPorId(idtiposervico);
		ts.setServico(tiposervico.getServico());
		return new TipoServicoDTO(repo.save(tiposervico));
	}
	
	public void excluirTipoServico(Long tiposervico) {
		repo.deleteById(tiposervico);
	}
}
