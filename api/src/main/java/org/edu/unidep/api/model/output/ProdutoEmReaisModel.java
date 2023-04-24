package org.edu.unidep.api.model.output;

import java.math.BigDecimal;

public class ProdutoEmReaisModel {

	private String descricao;
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
