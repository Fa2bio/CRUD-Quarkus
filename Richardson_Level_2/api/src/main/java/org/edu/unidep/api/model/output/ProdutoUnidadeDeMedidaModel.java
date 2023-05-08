package org.edu.unidep.api.model.output;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoUnidadeDeMedidaModel {

	@Schema(example = "1", required = true)
	private Long id;
	
	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "KG", required = true)
	private String unidadeMedida;
	
	@Schema(example = "20", required = true)
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
