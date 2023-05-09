package org.edu.unidep.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public FotoNaoEncontradaException(Long id) {
		super(String.format("Não existe foto para o produto de id: %d.", id));
	}

}
