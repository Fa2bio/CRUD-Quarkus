package org.edu.unidep.api.assembler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.edu.unidep.api.model.input.ProdutoInput;
import org.edu.unidep.domain.model.Produto;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProdutoInputDisassembler {

	@Inject
	private ModelMapper modelMapper;
	
	public Produto toDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
}
