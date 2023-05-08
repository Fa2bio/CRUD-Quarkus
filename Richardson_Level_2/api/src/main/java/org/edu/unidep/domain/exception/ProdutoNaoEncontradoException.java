package org.edu.unidep.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException(Long id) {
		super(String.format("Não existe produto de id: %d.", id));
	}

}
