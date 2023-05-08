package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.edu.unidep.domain.exception.EntidadeNaoEncontradaException;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<Exception>{

	private static String defaultMessage = "O servidor encontrou um erro inesperado e não consiguiu completar sua requisição."
			+ "Por favor, entre em contato com o adminstrador do sistema.";
	
	@Override
	public Response toResponse(Exception exception) {
		if(exception instanceof EntidadeNaoEncontradaException) {
			return Response.status(Status.NOT_FOUND)
					.entity(createExceptionMessage(exception, exception.getMessage()))
					.build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(createExceptionMessage(exception, defaultMessage))
				.build();
	}
	
	private ExceptionMessage createExceptionMessage(Exception exception, String message) {
		ExceptionMessage e = new ExceptionMessage();
		e.setTimeStamp(LocalDateTime.now());
		e.setType(exception.getClass().getSimpleName());
		e.setUserMessage(message);
		return e;
	}

}
