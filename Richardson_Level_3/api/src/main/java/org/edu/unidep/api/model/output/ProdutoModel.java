package org.edu.unidep.api.model.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoModel {

	@Schema(example = "1", required = true)
	private Long id;
	
	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "KG", required = true)
	private String unidadeMedida;
	
	@Schema(example = "10/01/2020", required = true)
	private String dataVencimento;
	
	@Schema(example = "10", required = true)
	private Integer totalDeVendas;
	
	@Schema(example = "29.90", required = true)
	private BigDecimal preco;
	
	@Schema(example = "5", required = true)
	private Integer estoque;
	
	private List<Link> links = new ArrayList<>();
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
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
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
