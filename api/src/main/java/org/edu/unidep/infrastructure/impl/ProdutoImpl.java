package org.edu.unidep.infrastructure.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProdutoImpl implements PanacheRepository<Produto>, ProdutoRepository{

	@Override
	public List<Produto> listarProdutos() {
		return listAll();
	}

	@Override
	public Optional<Produto> buscarPessoaPeloCodigo(Long id) {
		return findByIdOptional(id);
	}

	@Override
	@Transactional
	public void salvar(Produto produto) {
		persist(produto);		
	}

	@Override
	@Transactional
	public void deletar(Produto produto) {
		delete(produto);
	}
}
