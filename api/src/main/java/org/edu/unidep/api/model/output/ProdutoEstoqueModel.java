package org.edu.unidep.api.model.output;

public class ProdutoEstoqueModel {

	private String descricao;
	private String unidadeMedida;
	private Integer totalEmEstoque;	

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
	public Integer getTotalEmEstoque() {
		return totalEmEstoque;
	}
	public void setTotalEmEstoque(Integer totalEmEstoque) {
		this.totalEmEstoque = totalEmEstoque;
	}
	
	
}
