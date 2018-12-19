package ${groupId}.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author nkrishnamurthy
 * Exception handler to map all the generic exceptions
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	} 
}