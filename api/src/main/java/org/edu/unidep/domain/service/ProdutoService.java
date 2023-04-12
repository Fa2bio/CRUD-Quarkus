package org.edu.unidep.domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoService {

	@Inject
	private ProdutoRepository produtoRepository;

	@Transactional
	public void registrar(Produto produto) {
		produtoRepository.salvar(produto);
	}
	
	@Transactional
	public Produto atualizar(Long id, Produto produtoAtualizado) {
		Produto produtoEncontrado = acharOuFalhar(id);
		produtoEncontrado.setDescricao(produtoAtualizado.getDescricao());
		produtoEncontrado.setDataVencimento(produtoAtualizado.getDataVencimento());;
		produtoEncontrado.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());;
		
		return produtoEncontrado;
	}
	
	@Transactional
	public void deletarProduto(Long id) {
		Produto produtoEncontrado = acharOuFalhar(id);
		produtoRepository.deletar(produtoEncontrado);
	}
	
	public Produto acharOuFalhar(Long id) {
		return produtoRepository.buscarPessoaPeloCodigo(id).get();
	}
}
