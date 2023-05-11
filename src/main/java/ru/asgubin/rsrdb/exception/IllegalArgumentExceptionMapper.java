package ru.asgubin.rsrdb.exception;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    @Produces({MediaType.TEXT_PLAIN})
    public Response toResponse(final IllegalArgumentException exception) {
        return Response.status(Response.Status.EXPECTATION_FAILED).entity(build(exception.getMessage()))
                .build();
    }

    private RestErrorResponse build(String message) {
        RestErrorResponse response = new RestErrorResponse();
        response.setMessage("an illegal argument was provided: " + message);
        return response;
    }
}
