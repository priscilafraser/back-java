package com.pet.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.pet.entidades.Categoria;
import com.pet.entidades.Fornecedor;
import com.pet.entidades.LigacaoFornecedorServico;
import com.pet.entidades.Servico;
import com.pet.entidades.TipoServico;

public class LigacaoFornecedorServicoDTO {

	private Long id;
	private float preco;
	private String descricao;
	private Fornecedor fornecedor;
	private TipoServico tiposervico;
	private Categoria categoria;
	private String horainicial;
	private String horafinal;
	
	
	
	public String getHorainicial() {
		return horainicial;
	}
	public void setHorainicial(String horainicial) {
		this.horainicial = horainicial;
	}
	public String getHorafinal() {
		return horafinal;
	}
	public void setHorafinal(String horafinal) {
		this.horafinal = horafinal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public TipoServico getTiposervico() {
		return tiposervico;
	}
	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}
	public LigacaoFornecedorServicoDTO() {
	
	}
	
		
	public LigacaoFornecedorServicoDTO(float preco, String descricao, Fornecedor fornecedor, TipoServico tiposervico,
			Categoria categoria, String horainicial, String horafinal) {
		super();
		this.preco = preco;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
		this.tiposervico = tiposervico;
		this.categoria = categoria;
		this.horainicial = horainicial;
		this.horafinal = horafinal;
	}
	
	
	public LigacaoFornecedorServicoDTO(LigacaoFornecedorServico ligacaofornecedorservico) {
		this.id = ligacaofornecedorservico.getId();
		this.preco = ligacaofornecedorservico.getPreco();
		this.categoria = ligacaofornecedorservico.getCategoria();
		this.descricao = ligacaofornecedorservico.getDescricao();
		this.fornecedor = ligacaofornecedorservico.getFornecedor();
		this.tiposervico = ligacaofornecedorservico.getTiposervico();
		this.horainicial = ligacaofornecedorservico.getHorainicial();
		this.horafinal = ligacaofornecedorservico.getHorafinal();
		
	}
	
	public static List<LigacaoFornecedorServicoDTO> converteParaDTO(List<LigacaoFornecedorServico> ligacaofornecedorservicos) {
		List<LigacaoFornecedorServicoDTO> ligacaofornecedorservicosDTO = new ArrayList<>();
		for(LigacaoFornecedorServico lsp: ligacaofornecedorservicos) {
			ligacaofornecedorservicosDTO.add(new LigacaoFornecedorServicoDTO(lsp));
		}
		return ligacaofornecedorservicosDTO;
	}
	
	
}
