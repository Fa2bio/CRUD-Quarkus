package org.edu.unidep.api.model.output;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoEmReaisModel {

	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "687.90", required = true)
	private BigDecimal vendasEmReais;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getVendasEmReais() {
		return vendasEmReais;
	}
	public void setVendasEmReais(BigDecimal vendasEmReais) {
		this.vendasEmReais = vendasEmReais;
	}
	
	
}
