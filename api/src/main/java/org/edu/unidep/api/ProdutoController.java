package org.edu.unidep.api;

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
import org.edu.unidep.domain.exception.ProdutoNaoEncontradoException;
import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.ProdutoRepository;
import org.edu.unidep.domain.service.ProdutoService;

@Path("/produtos")
public class ProdutoController {

	@Inject
	private ProdutoRepository produtoRepository;
	
	@Inject 
	private ProdutoService produtoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProdutos() {
		List<Produto> todasProdutos = produtoRepository.listarProdutos();
		if(todasProdutos.isEmpty()) return Response.noContent().build();
		return Response.ok(todasProdutos).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPessoa(@PathParam("id") Long id) {		
		try {
			Produto produtoEncontrado = produtoService.acharOuFalhar(id);
			return Response.ok(produtoEncontrado).build();
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@GET
	@Path("vendasUnidadeDeMedida/{id}")
	public Response listarVendasPorUnidadeMedida(@PathParam("id") Long id) {
		Produto produtoEncontrado;
		try {
			produtoEncontrado = produtoService.acharOuFalhar(id);
			String resultado = produtoRepository.buscarVendasPorUnidadeDeMedida(
					produtoEncontrado.getDescricao(), produtoEncontrado.getUnidadeMedida());
			return Response.ok(resultado).build();
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("vendasEmReais/{id}")
	public Response listarVendasEmReais(@PathParam("id") Long id) {
		Produto produtoEncontrado;
		try {
			produtoEncontrado = produtoService.acharOuFalhar(id);
			String resultado = produtoRepository.buscarVendasPorReais(
					produtoEncontrado.getDescricao());
			return Response.ok(resultado).build();
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("totalEmEstoque/{id}")
	public Response listarEmEstoque(@PathParam("id") Long id) {
		Produto produtoEncontrado;
		try {
			produtoEncontrado = produtoService.acharOuFalhar(id);
			String resultado = produtoRepository.buscarTotalEmEstoque(
					produtoEncontrado.getDescricao());
			return Response.ok(resultado).build();
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvar(@RequestBody Produto produto) {
		produtoService.registrar(produto);
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.atualizar(id, produto);
		return Response.ok(produtoAtualizado).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(@PathParam("id") Long id) {
		produtoService.deletarProduto(id);
		return Response.noContent().build();
	}
}
