package ru.asgubin.rsrdb.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<WebApplicationException> {
    public static final String HTTP_405_MESSAGE = "use one of";

    @Override
    public Response toResponse(final WebApplicationException exception) {
        String message = exception.getMessage();
        Response response = exception.getResponse();
        Status status = response.getStatusInfo().toEnum();

        switch (status) {
            case METHOD_NOT_ALLOWED:
                message = HTTP_405_MESSAGE + response.getAllowedMethods();
                break;
            case INTERNAL_SERVER_ERROR:
                message = "internal validation - " + exception;
                break;
            default:
                message = "[unhandled response code] " + exception;
        }

        return Response.status(status)
                .entity(status + ": " + message)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
