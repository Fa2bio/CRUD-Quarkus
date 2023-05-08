package org.edu.unidep.api.assembler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.api.model.input.PessoaViaCepInput;
import org.edu.unidep.domain.model.Pessoa;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PessoaInputDisassembler {

	@Inject
	private ModelMapper modelMapper;
	
	public Pessoa toDomainObject(PessoaInput pessoaInput) {
		return modelMapper.map(pessoaInput, Pessoa.class);
	}
	
	public Pessoa toDomainObjectViaCep(PessoaViaCepInput pessoaViaCepInput ) {
		return modelMapper.map(pessoaViaCepInput, Pessoa.class);
	}
	
	public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {
		modelMapper.map(pessoaInput, pessoa);
	}
}
