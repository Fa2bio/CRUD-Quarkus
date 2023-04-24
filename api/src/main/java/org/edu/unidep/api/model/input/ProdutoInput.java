package org.edu.unidep.api.model.input;

import java.math.BigDecimal;

public class ProdutoInput {

	private String descricao;
	private String unidadeMedida;
	private String dataVencimento;
	private Integer totalDeVendas;
	private BigDecimal preco;
	private Integer estoque;
	
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
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Integer getTotalDeVendas() {
		return totalDeVendas;
	}
	public void setTotalDeVendas(Integer totalDeVendas) {
		this.totalDeVendas = totalDeVendas;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
