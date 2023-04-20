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
import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.domain.exception.PessoaNaoEncontradaException;
import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;
import org.edu.unidep.domain.service.PessoaService;

@Path("/pessoas")
public class PessoaController {

	@Inject
	private PessoaRepository pessoaRepository;
	
	@Inject 
	private PessoaService pessoaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPessoa() {
		List<Pessoa> todasPessoas = pessoaRepository.listarPessoas();
		if(todasPessoas.isEmpty()) return Response.status(Status.NO_CONTENT).build();
		return Response.ok(todasPessoas).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPessoa(@PathParam("id") Long id) {
		try {
			Pessoa pessoaEncontrada = pessoaService.acharOuFalhar(id);
			return Response.ok(pessoaEncontrada).build();
		} catch (PessoaNaoEncontradaException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(@RequestBody Pessoa pessoa) {
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/viacep")
	public Response salvarPessoaViaCep(@RequestBody PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaService.enderecoViaCep(pessoaInput);
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, @RequestBody Pessoa pessoa) {
		Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
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
