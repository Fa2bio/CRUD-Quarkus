package org.edu.unidep.api.assembler;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

import org.edu.unidep.api.hypermedia.ApiLinks;
import org.edu.unidep.api.model.output.ProdutoEmReaisModel;
import org.edu.unidep.api.model.output.ProdutoEstoqueModel;
import org.edu.unidep.api.model.output.ProdutoModel;
import org.edu.unidep.api.model.output.ProdutoUnidadeDeMedidaModel;
import org.edu.unidep.domain.model.Produto;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProdutoOutputAssembler {

	@Inject
	private ModelMapper modelMapper;
	
	@Inject
	private ApiLinks apiLinks;
	
	public ProdutoModel toModel(Produto produto, UriInfo uriInfo) {
		ProdutoModel produtoModel = modelMapper.map(produto, ProdutoModel.class);
		produtoModel.addLink(apiLinks.linkToProdutos(uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosBuscar(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosUnidadeMedida(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosReais(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosListarEstoque(produto.getId(), uriInfo));
		return produtoModel;
	}
	
	public ProdutoUnidadeDeMedidaModel toUnidadeDeMedidaModel(Produto produto, UriInfo uriInfo) {
		System.out.println(uriInfo.getPath().toString());
		ProdutoUnidadeDeMedidaModel produtoModel = modelMapper.map(produto, ProdutoUnidadeDeMedidaModel.class);
		produtoModel.addLink(apiLinks.linkToProdutos(uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosBuscar(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosUnidadeMedida(produto.getId(), uriInfo));
		return produtoModel;
	}
	
	public ProdutoEmReaisModel toReaisModel(Produto produto, UriInfo uriInfo) {
		ProdutoEmReaisModel produtoModel = modelMapper.map(produto, ProdutoEmReaisModel.class);
		produtoModel.addLink(apiLinks.linkToProdutos(uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosBuscar(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosReais(produto.getId(), uriInfo));
		return produtoModel;
	}
	
	public ProdutoEstoqueModel toEstoqueModel(Produto produto, UriInfo uriInfo) {
		ProdutoEstoqueModel produtoModel = modelMapper.map(produto, ProdutoEstoqueModel.class);
		produtoModel.addLink(apiLinks.linkToProdutos(uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosBuscar(produto.getId(), uriInfo));
		produtoModel.addLink(apiLinks.linkToProdutosListarEstoque(produto.getId(), uriInfo));
		return produtoModel;
	}
	
	public List<ProdutoModel> toCollectionModel(List<Produto> produtos, UriInfo uriInfo){
		List<ProdutoModel> produtosModel = new ArrayList<>();
		for (Produto produto: produtos) {
			produtosModel.add(toModel(produto, uriInfo));
		}
		return produtosModel;
	}
}
