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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.edu.unidep.api.assembler.PessoaInputDisassembler;
import org.edu.unidep.api.assembler.PessoaOutputAssembler;
import org.edu.unidep.api.exceptionhandler.ExceptionMessage;
import org.edu.unidep.api.model.input.PessoaInput;
import org.edu.unidep.api.model.input.PessoaViaCepInput;
import org.edu.unidep.api.model.output.PessoaModel;
import org.edu.unidep.domain.model.Endereco;
import org.edu.unidep.domain.model.Pessoa;
import org.edu.unidep.domain.repository.PessoaRepository;
import org.edu.unidep.domain.service.PessoaService;

@Path("/pessoas")
@Tag(name = "Pessoa")
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
	@Operation(description = "Lista Todas As Pessoas")
	public List<PessoaModel> listarPessoa(@Context UriInfo uriInfo) {
		List<Pessoa> todasPessoas = pessoaRepository.listarPessoas();		
		List<PessoaModel> todasPessoasModel = pessoaOutputAssembler.toCollectionModel(todasPessoas, uriInfo);
		return todasPessoasModel;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Busca Pessoa Por Id")
	@APIResponses({
		@APIResponse(responseCode = "404", description = "Pessoa Não Encontrada", content = 
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public PessoaModel buscarPessoaPorId(
			@Parameter(description = "Id da Pessoa", example = "1", required = true)
			@PathParam("id") Long id, @Context UriInfo uriInfo) {
		Pessoa pessoaEncontrada = pessoaService.acharOuFalhar(id);
		PessoaModel pessoaModel = pessoaOutputAssembler.toModel(pessoaEncontrada, uriInfo);
		return pessoaModel;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Registra Uma Pessoa Com Endereço Completo")
	@APIResponses({
		@APIResponse(responseCode = "201", description = "Pessoa Registrada"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content = 
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public Response salvar(
			@RequestBody(description = "Body", required = true)
			PessoaInput pessoaInput) {
		
		pessoaService.validarPessoaInput(pessoaInput);
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
		System.out.println(pessoa.getEndereco().getComplemento());
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Path("/viacep")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Registra Uma Pessoa Com Endereço Gerado Pela API ViaCep")
	@APIResponses({
		@APIResponse(responseCode = "201", description = "Pessoa Registrada"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content = 
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public Response salvarPessoaViaCep(
			@RequestBody(description = "Body", required = true)
			PessoaViaCepInput pessoaViaCepInput) {
		
		pessoaService.validarPessoaInput(pessoaViaCepInput);
		Endereco endereco = pessoaService.enderecoViaCep(pessoaViaCepInput.getCep());
		Pessoa pessoa = pessoaInputDisassembler.toDomainObjectViaCep(pessoaViaCepInput);
		pessoa.setEndereco(endereco);
		pessoaService.registrar(pessoa);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Atualiza Um Registro De Pessoa Por Id")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "Pessoa Atualizada"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content = 
			@Content(schema = @Schema(implementation = ExceptionMessage.class))),
		@APIResponse(responseCode = "404", description = "Pessoa Não Encontrada")
	})
	public Response atualizar(
			@Parameter(description = "Id da Pessoa", example = "1", required = true)
			@PathParam("id") Long id,
			
			@RequestBody(description = "Body", required = true)  PessoaInput pessoaInput) {
		
		pessoaService.validarPessoaInput(pessoaInput);
		Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoaInput);
		return Response.ok(pessoaAtualizada).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Deleta Um Registro De Pessoa Por Id")
	@APIResponses({
		@APIResponse(responseCode = "204", description = "Pessoa Excluída"),
		@APIResponse(responseCode = "404", description = "Pessoa Não Encontrada")
	})
	public Response deletar(
			@Parameter(description = "Id da Pessoa", example = "1", required = true)
			@PathParam("id") Long id) {
		pessoaService.deletarPessoa(id);
		return Response.noContent().build();
	}
}
