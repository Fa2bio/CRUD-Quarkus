package org.edu.unidep.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.edu.unidep.api.model.input.FotoProdutoInput;
import org.edu.unidep.domain.model.FotoProduto;
import org.edu.unidep.domain.model.Produto;
import org.edu.unidep.domain.repository.FotoStorageRepository;
import org.edu.unidep.domain.service.FotoProdutoService;
import org.edu.unidep.domain.service.ProdutoService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("produtos/foto")
public class FotoProdutoController {

	@Inject
	private FotoProdutoService fotoProdutoService;
	
	@Inject
	private FotoStorageRepository fotoStorageRepository;
	
	@Inject
	private ProdutoService produtoService;
	
	@GET
	@Path("/produto/{produtoId}")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response recuperarFoto(@PathParam("produtoId") Long produtoId) {
		FotoProduto foto = fotoProdutoService.acharOuFalhar(produtoId);
		MediaType mediaTypeFoto = MediaType.valueOf(foto.getContentType());
		System.out.println(mediaTypeFoto.toString());
		java.io.InputStream inputStream = fotoStorageRepository.toRecover(foto.getFileName());
		return Response.ok(inputStream, mediaTypeFoto).build();
	}
	
	@POST
	@Path("/produto/{produtoId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public FotoProduto registrarFoto(@PathParam("produtoId") Long produtoId,
			@MultipartForm MultipartFormDataInput input) throws IOException {
		Produto produto = produtoService.acharOuFalhar(produtoId);
		FotoProdutoInput fotoProdutoInput = new FotoProdutoInput();
		fotoProdutoInput.setDescricao("Maminha");
		fotoProdutoInput.setFile(new FileInputStream("maminha.jpg"));
		
		InputStream file = fotoProdutoInput.getFile();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType("image/jpeg");
		foto.setFileName(file.toString());
		
		foto = fotoProdutoService.salvarFoto(foto, file);
		
		return foto;
	}
}
