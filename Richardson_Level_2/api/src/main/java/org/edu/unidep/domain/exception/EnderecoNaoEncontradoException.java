package org.edu.unidep.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public EnderecoNaoEncontradoException(String cep) {
		super(String.format("O endereço de cep: %s, não pode ser encontrado.", cep));
	}

}
