package org.edu.unidep.api.controller;

import java.math.BigDecimal;
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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.edu.unidep.api.assembler.ProdutoInputDisassembler;
import org.edu.unidep.api.assembler.ProdutoOutputAssembler;
import org.edu.unidep.api.exceptionhandler.ExceptionMessage;
import org.edu.unidep.api.model.input.ProdutoInput;
import org.edu.unidep.api.model.output.ProdutoEmReaisModel;
import org.edu.unidep.api.model.output.ProdutoEstoqueModel;
import org.edu.unidep.api.model.output.ProdutoModel;
import org.edu.unidep.api.model.output.ProdutoUnidadeDeMedidaModel;
import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;
import org.edu.unidep.domain.service.ProdutoService;

@Path("/produtos")
@Tag(name = "Produto")
public class ProdutoController{

	@Inject
	private ProdutoRepository produtoRepository;
	
	@Inject 
	private ProdutoService produtoService;
	
	@Inject
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Inject
	private ProdutoOutputAssembler produtoOutputAssembler;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Lista Todos Os Produtos")
	public List<ProdutoModel> listarProdutos() {
		List<Produto> todasProdutos = produtoRepository.listarProdutos();
		List<ProdutoModel> todasProdutosModel = produtoOutputAssembler.toCollectionModel(todasProdutos);
		return todasProdutosModel;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Buscar Produto Por Id")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoModel buscarProduto(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id) {		
		Produto produtoEncontrado = produtoService.acharOuFalhar(id);
		ProdutoModel produtoModel = produtoOutputAssembler.toModel(produtoEncontrado);
		return produtoModel;		
	}
	
	@GET
	@Path("vendasUnidadeDeMedida/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Lista O Total De Vendas De Um Produto Por Unidade De Medida")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoUnidadeDeMedidaModel listarVendasPorUnidadeMedida(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id) {
		
		Produto produtoEncontrado = produtoService.acharOuFalhar(id);
		String resultado = produtoRepository.buscarVendasPorUnidadeDeMedida(
				produtoEncontrado.getDescricao(), produtoEncontrado.getUnidadeMedida());
		
		Integer valor = Integer.valueOf(resultado);
		ProdutoUnidadeDeMedidaModel produtoModel = produtoOutputAssembler.toUnidadeDeMedidaModel(produtoEncontrado);
		produtoModel.setVendasPorUnidadeMedida(valor);
		
		return produtoModel;

	}
	
	@GET
	@Path("vendasEmReais/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Lista O Total De Vendas De Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoEmReaisModel listarVendasEmReais(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id) {
		
		Produto produtoEncontrado = produtoService.acharOuFalhar(id);
		String resultado = produtoRepository.buscarVendasPorReais(
				produtoEncontrado.getDescricao());

		Double d = Double.valueOf(resultado);
		BigDecimal valor = BigDecimal.valueOf(d).setScale(2);		
		
		ProdutoEmReaisModel produtoModel = produtoOutputAssembler.toReaisModel(produtoEncontrado);
		produtoModel.setVendasEmReais(valor);
		return produtoModel;	
	}
	
	@GET
	@Path("totalEmEstoque/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Lista O Total Em Estoque De Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoEstoqueModel listarEmEstoque(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id) {
		
		Produto produtoEncontrado = produtoService.acharOuFalhar(id);
		String resultado = produtoRepository.buscarTotalEmEstoque(
				produtoEncontrado.getDescricao());
		
		Integer valor = Integer.valueOf(resultado);
		ProdutoEstoqueModel produtoModel = produtoOutputAssembler.toEstoqueModel(produtoEncontrado);
		produtoModel.setTotalEmEstoque(valor);
		
		return produtoModel;	
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Registra Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "201", description = "Created"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public Response salvar(@RequestBody(description = "Body", required = true) ProdutoInput produtoInput) {
		produtoService.validarProdutoInput(produtoInput);
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produtoService.registrar(produto);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Atualiza Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "Ok"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class))),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public ProdutoModel atualizar(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id,
			@RequestBody(description = "Body", required = true)
			ProdutoInput produtoInput) {
		produtoService.validarProdutoInput(produtoInput);
		Produto produto = produtoService.atualizar(id, produtoInput);
		ProdutoModel produtoModel = produtoOutputAssembler.toModel(produto);
		return produtoModel;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Deletar Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "204", description = "No Content"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public Response deletar(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id) {
		produtoService.deletarProduto(id);
		return Response.noContent().build();
	}
}
