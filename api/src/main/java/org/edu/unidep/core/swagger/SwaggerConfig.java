package org.edu.unidep.core.swagger;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
	    tags = {
	            @Tag(name="Produto", description="Gerencia a Entidade Produto."),
	            @Tag(name="Pessoa", description="Gerencia a Entidade Pessoa.")
	    },
	    info = @Info(
	        title="CRUD-Quarkus",
	        version = "1.0.1",
	        contact = @Contact(
	            name = "Fábio",
	            url = "www.github.com/Fa2bio",
	            email = "fabio.s0@hotmail.com"),
	        license = @License(
	            name = "Apache 2.0",
	            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
	)
public class SwaggerConfig extends Application{

}
