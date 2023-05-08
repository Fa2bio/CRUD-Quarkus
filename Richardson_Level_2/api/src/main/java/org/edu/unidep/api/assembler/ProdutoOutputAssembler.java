package org.edu.unidep.api.assembler;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
	
	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}
	
	public ProdutoUnidadeDeMedidaModel toUnidadeDeMedidaModel(Produto produto) {
		return modelMapper.map(produto, ProdutoUnidadeDeMedidaModel.class);
	}
	
	public ProdutoEmReaisModel toReaisModel(Produto produto) {
		return modelMapper.map(produto, ProdutoEmReaisModel.class);
	}
	
	public ProdutoEstoqueModel toEstoqueModel(Produto produto) {
		return modelMapper.map(produto, ProdutoEstoqueModel.class);
	}
	
	public List<ProdutoModel> toCollectionModel(List<Produto> produtos){
		List<ProdutoModel> produtosModel = new ArrayList<>();
		for (Produto produto: produtos) {
			produtosModel.add(toModel(produto));
		}
		return produtosModel;
	}
}
