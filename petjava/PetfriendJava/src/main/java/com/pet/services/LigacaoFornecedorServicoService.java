package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.entidades.LigacaoFornecedorServico;
import com.pet.repository.LigacaoFornecedorServicoRepository;
import com.pet.services.dto.LigacaoFornecedorProdutoDTO;
import com.pet.services.dto.LigacaoFornecedorServicoDTO;

@Service
public class LigacaoFornecedorServicoService {
	
	@Autowired
	LigacaoFornecedorServicoRepository repo;
	
	public LigacaoFornecedorServicoDTO salvar(LigacaoFornecedorServico ligacaofornecedorservico) {
		LigacaoFornecedorServico lsp = repo.save(ligacaofornecedorservico);
		LigacaoFornecedorServicoDTO ligacaofornecedorservicoDTO = new LigacaoFornecedorServicoDTO(lsp); 
		return ligacaofornecedorservicoDTO;
	}
	
	public List<LigacaoFornecedorServicoDTO> consultarLigacaoFornecedorServico() {
		List<LigacaoFornecedorServico> ligacaofornecedorservico = repo.findAll();
		List<LigacaoFornecedorServicoDTO> ligacaofornecedorservicoDTO = new ArrayList();
		for(LigacaoFornecedorServico lsp: ligacaofornecedorservico) {
			ligacaofornecedorservicoDTO.add(new LigacaoFornecedorServicoDTO(lsp));    //recebe como construtor o ct
		}
		return ligacaofornecedorservicoDTO;	
	}
	
	public LigacaoFornecedorServicoDTO consultarLigacaoFornecedorServicoPorId(Long idligacaofornecedorservico) {
		Optional<LigacaoFornecedorServico> opligacaofornecedorservico = repo.findById(idligacaofornecedorservico);
		LigacaoFornecedorServico lsp = opligacaofornecedorservico.orElseThrow(() -> new EntityNotFoundException("Não encontrado"));
		return new LigacaoFornecedorServicoDTO(lsp);	
	}
	
	public LigacaoFornecedorServicoDTO alterarLigacaoFornecedorServico(Long idligacaofornecedorservico, LigacaoFornecedorServico ligacaofornecedorservico) {
		LigacaoFornecedorServicoDTO lsp = consultarLigacaoFornecedorServicoPorId(idligacaofornecedorservico);
		lsp.setPreco(ligacaofornecedorservico.getPreco());
		lsp.setDescricao(ligacaofornecedorservico.getDescricao());
		lsp.setFornecedor(ligacaofornecedorservico.getFornecedor());
		lsp.setTiposervico(ligacaofornecedorservico.getTiposervico());
		lsp.setCategoria(ligacaofornecedorservico.getCategoria());
		lsp.setHorainicial(ligacaofornecedorservico.getHorainicial());
		lsp.setHorafinal(ligacaofornecedorservico.getHorafinal());
		return new LigacaoFornecedorServicoDTO(repo.save(ligacaofornecedorservico));
	}
	
	public void excluirLigacaoFornecedorServico(Long idligacaofornecedorservico) {
		repo.deleteById(idligacaofornecedorservico);
	}
	
	
	///////////////////////PERSONALIZADO
	public List<LigacaoFornecedorServicoDTO> consultarServicosPorFornecedor(Long idfornecedor) {
	return LigacaoFornecedorServicoDTO.converteParaDTO(repo.getServicosPorFornecedor(idfornecedor));
	}
	
}
