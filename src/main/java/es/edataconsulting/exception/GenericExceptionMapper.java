package es.edataconsulting.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.edataconsulting.demo.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500);
		return Response
				.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

	
}
