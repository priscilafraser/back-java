package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entidades.TipoProduto;
import com.pet.repository.TipoProdutoRepository;
import com.pet.services.dto.TipoProdutoDTO;

@Service
public class TipoProdutoService {

	@Autowired
	TipoProdutoRepository repo;
	
	public TipoProdutoDTO salvar(TipoProduto tipoproduto) {
		TipoProduto tp = repo.save(tipoproduto);
		TipoProdutoDTO tipoprodutoDTO = new TipoProdutoDTO(tp); 
		return tipoprodutoDTO;
	}
	
	public List<TipoProdutoDTO> consultarTipoProduto() {
		List<TipoProduto> tipoproduto = repo.findAll();
		List<TipoProdutoDTO> tipoprodutoDTO = new ArrayList();
		for(TipoProduto tp: tipoproduto) {
			tipoprodutoDTO.add(new TipoProdutoDTO(tp));    //recebe como construtor o ct
		}
		return tipoprodutoDTO;	
	}
	
	public TipoProdutoDTO consultarTipoProdutoPorId(Long idtipoproduto) {
		Optional<TipoProduto> optipoproduto = repo.findById(idtipoproduto);
		TipoProduto tp = optipoproduto.orElseThrow(() -> new EntityNotFoundException("Tipo do produto nao encontrado"));
		return new TipoProdutoDTO(tp);	
	}
	
	public TipoProdutoDTO alterarTipoProduto(Long idtipoproduto, TipoProduto tipoproduto) {
		TipoProdutoDTO tp = consultarTipoProdutoPorId(idtipoproduto);
		tp.setProduto(tipoproduto.getProduto());
		return new TipoProdutoDTO(repo.save(tipoproduto));
	}
	
	public void excluirTipoProduto(Long idtipoproduto) {
		repo.deleteById(idtipoproduto);
	}
	
	
}
