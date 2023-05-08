package org.edu.unidep.api.model.output;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoEstoqueModel {

	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "KG", required = true)
	private String unidadeMedida;
	
	@Schema(example = "10", required = true)
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
