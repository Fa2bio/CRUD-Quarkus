package org.edu.unidep.api.assembler;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

import org.edu.unidep.api.hypermedia.ApiLinks;
import org.edu.unidep.api.model.output.PessoaModel;
import org.edu.unidep.domain.model.Pessoa;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PessoaOutputAssembler {

	@Inject
	private ModelMapper modelMapper;
	
	@Inject
	private ApiLinks apiLinks;
	
	public PessoaModel toModel(Pessoa pessoa, UriInfo uriInfo) {		
		PessoaModel pessoaModel = modelMapper.map(pessoa, PessoaModel.class);
		pessoaModel.addLink(apiLinks.linkToPessoas(uriInfo));
		pessoaModel.addLink(apiLinks.linkToPessoasBuscar(pessoa.getId(), uriInfo));
		return pessoaModel;
	}
	
	public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas, UriInfo uriInfo){
		List<PessoaModel> pessoasModel = new ArrayList<>();
		for (Pessoa pessoa: pessoas) {
			pessoasModel.add(toModel(pessoa, uriInfo));
		}
		return pessoasModel;
	}
}
