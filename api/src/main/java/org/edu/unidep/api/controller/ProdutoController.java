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

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.edu.unidep.api.assembler.ProdutoInputDisassembler;
import org.edu.unidep.api.assembler.ProdutoOutputAssembler;
import org.edu.unidep.api.model.input.ProdutoInput;
import org.edu.unidep.api.model.output.ProdutoEmReaisModel;
import org.edu.unidep.api.model.output.ProdutoEstoqueModel;
import org.edu.unidep.api.model.output.ProdutoModel;
import org.edu.unidep.api.model.output.ProdutoUnidadeDeMedidaModel;
import org.edu.unidep.api.swaggeropenapi.ProdutoSwagger;
import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;
import org.edu.unidep.domain.service.ProdutoService;

@Path("/produtos")
@Tag(name = "Produto")
public class ProdutoController implements ProdutoSwagger{

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
	public List<ProdutoModel> listarProdutos() {
		List<Produto> todasProdutos = produtoRepository.listarProdutos();
		List<ProdutoModel> todasProdutosModel = produtoOutputAssembler.toCollectionModel(todasProdutos);
		return todasProdutosModel;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoModel buscarProduto(@PathParam("id") Long id) {		
		Produto produtoEncontrado = produtoService.acharOuFalhar(id);
		ProdutoModel produtoModel = produtoOutputAssembler.toModel(produtoEncontrado);
		return produtoModel;		
	}
	
	@GET
	@Path("vendasUnidadeDeMedida/{id}")
	public ProdutoUnidadeDeMedidaModel listarVendasPorUnidadeMedida(@PathParam("id") Long id) {
		
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
	public ProdutoEmReaisModel listarVendasEmReais(@PathParam("id") Long id) {
		
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
	public ProdutoEstoqueModel listarEmEstoque(@PathParam("id") Long id) {
		
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
	public Response salvar(@RequestBody ProdutoInput produtoInput) {
		produtoService.validarProdutoInput(produtoInput);
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produtoService.registrar(produto);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto atualizar(@PathParam("id") Long id, @RequestBody ProdutoInput produtoInput) {
		produtoService.validarProdutoInput(produtoInput);
		Produto produto = produtoService.atualizar(id, produtoInput);
		return produto;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("id") Long id) {
		produtoService.deletarProduto(id);
		return Response.noContent().build();
	}
}
