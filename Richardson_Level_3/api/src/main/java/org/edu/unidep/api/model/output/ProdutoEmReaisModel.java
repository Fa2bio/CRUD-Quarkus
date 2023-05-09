package org.edu.unidep.api.model.output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProdutoEmReaisModel {

	@Schema(example = "PICANHA", required = true)
	private String descricao;
	
	@Schema(example = "687.90", required = true)
	private BigDecimal vendasEmReais;
	
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
	public BigDecimal getVendasEmReais() {
		return vendasEmReais;
	}
	public void setVendasEmReais(BigDecimal vendasEmReais) {
		this.vendasEmReais = vendasEmReais;
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
