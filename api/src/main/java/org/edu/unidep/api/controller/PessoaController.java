package org.edu.unidep.api.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.edu.unidep.api.assembler.PessoaInputDisassembler;
import org.edu.unidep.api.assembler.PessoaOutputAssembler;
import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.api.model.input.PessoaViaCepInput;
import org.edu.unidep.api.model.output.PessoaModel;
import org.edu.unidep.domain.model.Endereco;
import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;
import org.edu.unidep.domain.service.PessoaService;

@Path("/pessoas")
public class PessoaController {

	@Inject
	private PessoaRepository pessoaRepository;
	
	@Inject 
	private PessoaService pessoaService;
	
	@Inject
	private PessoaOutputAssembler pessoaOutputAssembler;
	
	@Inject
	private PessoaInputDisassembler pessoaInputDisassembler;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PessoaModel> listarPessoa() {
		List<Pessoa> todasPessoas = pessoaRepository.listarPessoas();		
		List<PessoaModel> todasPessoasModel = pessoaOutputAssembler.toCollectionModel(todasPessoas);
		return todasPessoasModel;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PessoaModel buscarPessoa(@PathParam("id") Long id) {
		Pessoa pessoaEncontrada = pessoaService.acharOuFalhar(id);
		PessoaModel pessoaModel = pessoaOutputAssembler.toModel(pessoaEncontrada);
		return pessoaModel;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(@RequestBody PessoaInput pessoaInput) {
		pessoaService.validarPessoaInput(pessoaInput);
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
		System.out.println(pessoa.getEndereco().getComplemento());
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/viacep")
	public Response salvarPessoaViaCep(@RequestBody PessoaViaCepInput pessoaViaCepInput) {
		Endereco endereco = pessoaService.enderecoViaCep(pessoaViaCepInput.getCep());
		Pessoa pessoa = pessoaInputDisassembler.toDomainObjectViaCep(pessoaViaCepInput);
		pessoa.setEndereco(endereco);
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoaInput);
		return Response.ok(pessoaAtualizada).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("id") Long id) {
		pessoaService.deletarPessoa(id);
		return Response.noContent().build();
	}
}
