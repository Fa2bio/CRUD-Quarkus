package org.edu.unidep.api.model.input;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FotoProdutoInput {

	@FormParam("file")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	private InputStream file;
	
	@FormParam("descricao")
	private String descricao;

	public InputStream getFile() {
		return file;
	}
	
	public void setFile(InputStream file) {
		this.file = file;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
