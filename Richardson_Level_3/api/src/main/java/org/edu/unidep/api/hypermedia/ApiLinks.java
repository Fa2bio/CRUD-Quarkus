package org.edu.unidep.api.hypermedia;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.edu.unidep.api.controller.PessoaController;
import org.edu.unidep.api.controller.ProdutoController;

@ApplicationScoped
public class ApiLinks {

	public Link linkToPessoas(UriInfo uriInfo) {
		
		String uri = uriInfo.getBaseUriBuilder()
				.path(PessoaController.class)
				.build()
				.toString();
		
		Link link = linkBuilder(uri, "_blank");
		return link;
	}
	
	public Link linkToPessoasBuscar(Long pessoaId, UriInfo uriInfo) {
		String uri = uriPessoaWithMethod(uriInfo, pessoaId, "buscarPessoaPorId");		
		Link link = linkBuilder(uri, "_self");
		return link;
	}

	public Link linkToProdutos(UriInfo uriInfo) {
		
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProdutoController.class)
				.build()
				.toString();
		
		Link link = linkBuilder(uri, "_blank");
		return link;
	}
	
	public Link linkToProdutosBuscar(Long produtoId, UriInfo uriInfo) {
		
		String uri = uriProdutoWithMethod(uriInfo, produtoId, "buscarProdutoPorId");
		
		Link link = linkBuilder(uri, "_self");
		return link;
	}
	
	public Link linkToProdutosUnidadeMedida(Long produtoId, UriInfo uriInfo) {
		String uri = uriProdutoWithMethod(uriInfo, produtoId, "listarVendasPorUnidadeMedida");		
		Link link = linkBuilder(uri, "_self");
		return link;
	}
	
	public Link linkToProdutosReais(Long produtoId, UriInfo uriInfo) {
		String uri = uriProdutoWithMethod(uriInfo, produtoId, "listarVendasEmReais");		
		Link link = linkBuilder(uri, "_self");
		return link;
	}
	
	public Link linkToProdutosListarEstoque(Long produtoId, UriInfo uriInfo) {
		String uri = uriProdutoWithMethod(uriInfo, produtoId, "listarEmEstoque");		
		Link link = linkBuilder(uri, "_self");
		return link;
	}
	
	private String uriPessoaWithMethod(UriInfo uriInfo, Long pessoaId, String method) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(PessoaController.class)
				.path(PessoaController.class, method)
				.resolveTemplate("id", pessoaId)
				.build()
				.toString();
		return uri;
	}

	private String uriProdutoWithMethod(UriInfo uriInfo, Long produtoId, String method) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProdutoController.class)
				.path(ProdutoController.class, method)
				.resolveTemplate("id", produtoId)
				.build()
				.toString();
		return uri;
	}
	
	private Link linkBuilder(String uri, String rel) {
		return Link.fromUriBuilder(
				UriBuilder.fromUri(uri))
				.rel(rel)
				.build();
	}
}
