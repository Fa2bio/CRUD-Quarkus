package org.edu.unidep.api.model.output;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoEstoqueModel {

	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "KG", required = true)
	private String unidadeMedida;
	
	@Schema(example = "10", required = true)
	private Integer totalEmEstoque;
	
	private List<Link> links = new ArrayList<>();
	
	public void addLink(Link link) {
		this.links.add(link);
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
	public Integer getTotalEmEstoque() {
		return totalEmEstoque;
	}
	public void setTotalEmEstoque(Integer totalEmEstoque) {
		this.totalEmEstoque = totalEmEstoque;
	}
	
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
