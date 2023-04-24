package org.edu.unidep.api.model.output;

public class ProdutoUnidadeDeMedidaModel {

	private Long id;
	private String descricao;
	private String unidadeMedida;
	private Integer vendasPorUnidadeMedida;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public Integer getVendasPorUnidadeMedida() {
		return vendasPorUnidadeMedida;
	}
	public void setVendasPorUnidadeMedida(Integer vendasPorUnidadeMedida) {
		this.vendasPorUnidadeMedida = vendasPorUnidadeMedida;
	}
		
}
