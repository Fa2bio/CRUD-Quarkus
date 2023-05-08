package org.edu.unidep.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Set<ExceptionMessage.FieldError> fieldErrors = new LinkedHashSet<>();
		
		for (ConstraintViolation<?> erro : exception.getConstraintViolations()) {
			ExceptionMessage.FieldError e = new ExceptionMessage.FieldError(erro.getPropertyPath().toString(), erro.getMessage());
			fieldErrors.add(e);
		}
		ExceptionMessage erro = new ExceptionMessage();
		erro.setTimeStamp(LocalDateTime.now());
		erro.setType(exception.getClass().getSimpleName());
		erro.setUserMessage("As seguintes propriedades possuem valores inválidos");
		erro.setFieldErrors(fieldErrors);
		
		return Response.status(Status.BAD_REQUEST).entity(erro).build();
	}

}
