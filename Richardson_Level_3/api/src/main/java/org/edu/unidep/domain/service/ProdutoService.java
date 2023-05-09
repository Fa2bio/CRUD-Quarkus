package org.edu.unidep.domain.service;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.edu.unidep.api.assembler.ProdutoInputDisassembler;
import org.edu.unidep.api.model.input.ProdutoInput;
import org.edu.unidep.domain.exception.ProdutoNaoEncontradoException;
import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoService {

	@Inject
	private ProdutoRepository produtoRepository;

	@Inject
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Inject
	private Validator validator;
	
	@Transactional
	public void registrar(Produto produto) {
		produtoRepository.salvar(produto);
	}
	
	@Transactional
	public Produto atualizar(Long id, ProdutoInput produtoInput) {
		Produto produtoEncontrado = acharOuFalhar(id);
		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoEncontrado);
		return produtoEncontrado;
	}
	
	@Transactional
	public void deletarProduto(Long id) {
		Produto produtoEncontrado = acharOuFalhar(id);
		produtoRepository.deletar(produtoEncontrado);
	}
	
	public void validarProdutoInput(ProdutoInput produtoInput) {	
	
		Set<ConstraintViolation<ProdutoInput>> constraintViolations = validator.validate(produtoInput);
		if(constraintViolations.isEmpty()) return;
		else throw new ConstraintViolationException(constraintViolations);
		
	}
	
	public Produto acharOuFalhar(Long id) {
		return produtoRepository.buscarProdutoPeloCodigo(id)
				.orElseThrow(()-> new ProdutoNaoEncontradoException(id));
	}
}
