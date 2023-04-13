package org.edu.unidep.domain.exception;

public class PessoaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PessoaNaoEncontradaException(Long id) {
		super(String.format("Não existe cadastro de pessoa com o id %d", id));
	}

}
