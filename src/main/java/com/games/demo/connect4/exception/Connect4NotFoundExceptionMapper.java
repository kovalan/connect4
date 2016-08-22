package com.games.demo.connect4.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class Connect4NotFoundExceptionMapper implements ExceptionMapper<Connect4NotFoundException> {

	public Response toResponse(Connect4NotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(404, ex.getMessage());
		
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
