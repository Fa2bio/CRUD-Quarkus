package org.edu.unidep.domain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.domain.exception.EnderecoNaoEncontradoException;
import org.edu.unidep.domain.exception.PessoaNaoEncontradaException;
import org.edu.unidep.domain.model.Endereco;
import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;

import com.google.gson.Gson;

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
		return pessoaRepository.buscarPessoaPeloCodigo(id)
				.orElseThrow(()-> new PessoaNaoEncontradaException(id));
	}
	
	public Pessoa enderecoViaCep(PessoaInput pessoaInput) {
		try {
			URL url = new URL(String.format("https://viacep.com.br/ws/%s/json/", pessoaInput.getCep()));
			URLConnection connection = url.openConnection( );
			InputStream input = connection.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			StringBuilder jsonCep = new StringBuilder();
			
			String laco;
			
			while((laco = buffer.readLine())!=null) {
				jsonCep.append(laco);
			}
		
			Gson gson = new Gson();
			Endereco endereco = gson.fromJson(jsonCep.toString(), Endereco.class);
			if(endereco.getCep() == null) {
				throw new EnderecoNaoEncontradoException(pessoaInput.getCep());
			}
			
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(pessoaInput.getNome());
			pessoa.setSangue(pessoaInput.getSangue());
			pessoa.setCpf(pessoaInput.getCpf());
			pessoa.setDataAniversario(pessoaInput.getDataAniversario());
			pessoa.setEndereco(endereco);
			
			return pessoa;
			
		} catch (MalformedURLException e) {
			throw new EnderecoNaoEncontradoException(pessoaInput.getCep());
		} catch (IOException e) {
			throw new EnderecoNaoEncontradoException(pessoaInput.getCep());
		}
	}

}
