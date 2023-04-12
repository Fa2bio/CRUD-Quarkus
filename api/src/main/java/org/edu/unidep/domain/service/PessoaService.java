package org.edu.unidep.domain.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;

@ApplicationScoped
public class PessoaService {
	
	@Inject
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public void registrar(Pessoa pessoa) {
		pessoaRepository.salvar(pessoa);
	}
	
	@Transactional
	public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
		Pessoa pessoaEncontrada = acharOuFalhar(id);
		pessoaEncontrada.setNome(pessoaAtualizada.getNome());
		pessoaEncontrada.setSangue(pessoaAtualizada.getSangue());
		pessoaEncontrada.setDataAniversario(pessoaAtualizada.getDataAniversario());
		pessoaEncontrada.setEndereco(pessoaAtualizada.getEndereco());
		
		return pessoaEncontrada;
	}
	
	@Transactional
	public void deletarPessoa(Long id) {
		Pessoa pessoaEncontrada = acharOuFalhar(id);
		pessoaRepository.deletar(pessoaEncontrada);
	}
	
	public Pessoa acharOuFalhar(Long id) {
		return pessoaRepository.buscarPessoaPeloCodigo(id).get();
	}
}
