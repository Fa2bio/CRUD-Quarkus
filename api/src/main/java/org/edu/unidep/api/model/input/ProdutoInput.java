package org.edu.unidep.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoInput {

	@Schema(example = "PICANHA", required = true)
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String descricao;
	
	@Schema(example = "KG", required = true)
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String unidadeMedida;
	
	@Schema(example = "10/01/2020", required = true)
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	@NotBlank(message = "Nome da pessoa não pode estar em braco ou com apenas espaços.")
	private String dataVencimento;
	
	@Schema(example = "10", required = true)
	@PositiveOrZero(message = "Total de vendas deve ser maior ou igual a zero.")
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	private Integer totalDeVendas;
	
	@Schema(example = "29.90", required = true)
	@Positive(message = "Preço deve ser maior que zero.")
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
	private BigDecimal preco;
	
	@Schema(example = "15", required = true)
	@PositiveOrZero(message = "Estoque deve ser maior ou igual a zero.")
	@NotNull(message = "Nome da pessoa não pode ser nulo.")
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
