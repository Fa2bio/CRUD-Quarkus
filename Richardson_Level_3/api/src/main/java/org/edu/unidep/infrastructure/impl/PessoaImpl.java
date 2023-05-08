package org.edu.unidep.infrastructure.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PessoaImpl implements PanacheRepository<Pessoa>, PessoaRepository{

	@Override
	public List<Pessoa> listarPessoas() {
		return listAll();
	}

	@Override
	public Optional<Pessoa> buscarPessoaPeloCodigo(Long id) {
		return findByIdOptional(id);
	}

	@Override
	@Transactional
	public void salvar(Pessoa pessoa) {
		persist(pessoa);
	}

	@Override
	@Transactional
	public void deletar(Pessoa pessoa) {
		delete(pessoa);
	}	
}
