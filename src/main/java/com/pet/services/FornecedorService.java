package com.pet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entidades.Fornecedor;
import com.pet.repository.FornecedorRepository;
import com.pet.services.dto.FornecedorDTO;


@Service
public class FornecedorService {
	
	@Autowired
	FornecedorRepository repo;
	
	public FornecedorDTO salvar(Fornecedor fornecedor) {
		Fornecedor fr = repo.save(fornecedor);
		FornecedorDTO fornecedorDTO = new FornecedorDTO(fr); 
		return fornecedorDTO;
	}
	
	public List<FornecedorDTO> consultarFornecedor() {
		List<Fornecedor> fornecedor = repo.findAll();
		List<FornecedorDTO> fornecedorDTO = new ArrayList();
		for(Fornecedor fr: fornecedor) {
			fornecedorDTO.add(new FornecedorDTO(fr));    //recebe como construtor o ct
		}
		return fornecedorDTO;	
	}
	
	public FornecedorDTO consultarFornecedorPorId(Long idfornecedor) {
		Optional<Fornecedor> opfornecedor = repo.findById(idfornecedor);
		Fornecedor fr = opfornecedor.orElseThrow(() -> new EntityNotFoundException("Fornecedor nao encontrado"));
		return new FornecedorDTO(fr);	
	}
	
	public FornecedorDTO alterarFornecedor(Long idfornecedor, Fornecedor fornecedor) {
		FornecedorDTO fr = consultarFornecedorPorId(idfornecedor);
		fr.setRazaosocial(fornecedor.getRazaosocial());
		fr.setCnpj(fornecedor.getCnpj());
		fr.setTelefone(fornecedor.getTelefone());
		fr.setEmail(fornecedor.getEmail());
		fr.setCep(fornecedor.getCep());
		fr.setLogradouro(fornecedor.getLogradouro());
		fr.setNumero(fornecedor.getNumero());
		fr.setComplemento(fornecedor.getComplemento());
		fr.setBairro(fornecedor.getBairro());
		fr.setCidade(fornecedor.getCidade());
		fr.setEstado(fornecedor.getEstado());
		fr.setPerfil(fornecedor.getPerfil());
		fr.setSenha(fornecedor.getSenha());
		
		return new FornecedorDTO(repo.save(fornecedor));
	}
	
	public void excluirFornecedor(Long idfornecedor) {
		repo.deleteById(idfornecedor);
	}

}
