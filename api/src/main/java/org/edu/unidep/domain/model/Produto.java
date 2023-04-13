package org.edu.unidep.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_produto")
	private Long id;
	
	@NotNull
	@Column(name = "descricao", length = 800)
	private String descricao;
	
	@NotNull
	@Column(name = "unidade_de_medida", length = 10)
	private String unidadeMedida;
	
	@NotNull
	@Column(name = "data_de_vencimento", length = 10)
	private String dataVencimento;
	
	@NotNull
	@Column(name = "total_de_vendas")
	private Integer totalDeVendas;
	
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	@Column(name = "total_em_estoque")
	private Integer estoque;

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
