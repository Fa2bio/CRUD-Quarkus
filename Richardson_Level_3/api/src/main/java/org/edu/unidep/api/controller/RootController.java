package org.edu.unidep.api.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.edu.unidep.api.hypermedia.ApiLinks;
import org.edu.unidep.api.model.output.RootEntryPointModel;

@Path("")
public class RootController {

	@Inject
	private ApiLinks apiLinks;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RootEntryPointModel root(@Context UriInfo uriInfo) {
		var rootEntryPointModel = new RootEntryPointModel();
		rootEntryPointModel.addLink(apiLinks.linkToPessoas(uriInfo));
		rootEntryPointModel.addLink(apiLinks.linkToProdutos(uriInfo));
		
		return rootEntryPointModel;
	}
}
