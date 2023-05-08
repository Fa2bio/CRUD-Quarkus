package org.edu.unidep.api.assembler;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.edu.unidep.api.model.output.PessoaModel;
import org.edu.unidep.domain.model.Pessoa;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PessoaOutputAssembler {

	@Inject
	private ModelMapper modelMapper;
	
	public PessoaModel toModel(Pessoa Pessoa) {
		return modelMapper.map(Pessoa, PessoaModel.class);
	}
	
	public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas){
		List<PessoaModel> pessoasModel = new ArrayList<>();
		for (Pessoa pessoa: pessoas) {
			pessoasModel.add(toModel(pessoa));
		}
		return pessoasModel;
	}
}
