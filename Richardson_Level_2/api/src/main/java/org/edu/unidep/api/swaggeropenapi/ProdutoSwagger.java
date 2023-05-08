package org.edu.unidep.api.swaggeropenapi;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.edu.unidep.api.exceptionhandler.ExceptionMessage;
import org.edu.unidep.api.model.input.ProdutoInput;
import org.edu.unidep.api.model.output.ProdutoEmReaisModel;
import org.edu.unidep.api.model.output.ProdutoEstoqueModel;
import org.edu.unidep.api.model.output.ProdutoModel;
import org.edu.unidep.api.model.output.ProdutoUnidadeDeMedidaModel;

public interface ProdutoSwagger {

	@Operation(description = "Lista Todos Os Produtos")
	List<ProdutoModel> listarProdutos();
	
	
	@Operation(description = "Buscar Produto Por Id")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	ProdutoModel buscarProduto(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id);
	
	
	@Operation(description = "Lista O Total De Vendas De Um Produto Por Unidade De Medida")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	ProdutoUnidadeDeMedidaModel listarVendasPorUnidadeMedida(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id);
	
	@Operation(description = "Lista O Total De Vendas De Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoEmReaisModel listarVendasEmReais(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id);
	
	@Operation(description = "Lista O Total Em Estoque De Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "200", description = "ok"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public ProdutoEstoqueModel listarEmEstoque(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id);
	
	@Operation(description = "Registra Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "201", description = "Created"),
		@APIResponse(responseCode = "400", description = "Requisição Não Atendida", content =
				@Content(schema = @Schema(implementation = ExceptionMessage.class)))		
	})
	public Response salvar(@RequestBody(description = "Body", required = true) ProdutoInput produtoInput);
	
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
			ProdutoInput produtoInput);
	
	@Operation(description = "Deletar Um Produto")
	@APIResponses({
		@APIResponse(responseCode = "204", description = "No Content"),
		@APIResponse(responseCode = "404", description = "Produto Não Encontrado", content =
			@Content(schema = @Schema(implementation = ExceptionMessage.class)))
	})
	public Response deletar(
			@Parameter(description = "Id da Produto", example = "1", required = true)
			@PathParam("id") Long id);
}
