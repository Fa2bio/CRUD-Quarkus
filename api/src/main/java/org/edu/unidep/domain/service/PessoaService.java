package org.edu.unidep.domain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.edu.unidep.api.assembler.PessoaInputDisassembler;
import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.api.model.input.PessoaViaCepInput;
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
	
	@Inject
	private PessoaInputDisassembler pessoaInputDisassembler;
	
	@Transactional
	public void registrar(Pessoa pessoa) {
		pessoaRepository.salvar(pessoa);
	}
	
	@Transactional
	public Pessoa atualizar(Long id, PessoaInput pessoaInput) {
		Pessoa pessoaEncontrada = acharOuFalhar(id);
		pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaEncontrada);
		
		return pessoaEncontrada;
	}
	
	@Inject
	private Validator validator;
	
	@Transactional
	public void deletarPessoa(Long id) {
		Pessoa pessoaEncontrada = acharOuFalhar(id);
		pessoaRepository.deletar(pessoaEncontrada);
	}
	
	public <T> void validarPessoaInput(T pessoaInput) {
		
		if(pessoaInput instanceof PessoaInput) {
			Set<ConstraintViolation<PessoaInput>> constraintViolations = validator.validate((PessoaInput)pessoaInput);
			if(constraintViolations.isEmpty()) return;
			else throw new ConstraintViolationException(constraintViolations);
		}if(pessoaInput instanceof PessoaViaCepInput) {
			Set<ConstraintViolation<PessoaViaCepInput>> constraintViolations = validator.validate((PessoaViaCepInput)pessoaInput);
			if(constraintViolations.isEmpty()) return;
			else throw new ConstraintViolationException(constraintViolations);
		}

	}
	
	public Pessoa acharOuFalhar(Long id) {
		return pessoaRepository.buscarPessoaPeloCodigo(id)
				.orElseThrow(()-> new PessoaNaoEncontradaException(id));
	}
	
	public Endereco enderecoViaCep(String cep) {
		try {
			URL url = new URL(String.format("https://viacep.com.br/ws/%s/json/", cep));
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
				throw new EnderecoNaoEncontradoException(cep);
			}
			
			return endereco;
			
		} catch (MalformedURLException e) {
			throw new EnderecoNaoEncontradoException(cep);
		} catch (IOException e) {
			throw new EnderecoNaoEncontradoException(cep);
		}
	}

}
