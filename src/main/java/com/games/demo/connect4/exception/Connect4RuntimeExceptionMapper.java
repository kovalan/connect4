package com.games.demo.connect4.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class Connect4RuntimeExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(500, ex.getMessage());
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
