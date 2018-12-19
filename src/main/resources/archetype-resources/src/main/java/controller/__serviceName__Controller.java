package ${groupId}.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ${groupId}.prometheus.endpoints.PrometheusEndpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static ${groupId}.prometheus.endpoints.PrometheusEndpoint.numberOfRequests;
import static ${groupId}.prometheus.endpoints.PrometheusEndpoint.successCount;
import static ${groupId}.prometheus.endpoints.PrometheusEndpoint.failureCount;

/**
 * 
 * @author nkrishnamurthy 
 * 
 * Main Controller class to define the end points,
 *         request and response
 * 
 **/
@Component
@Path("/${path}")
@Api(value = "${serviceName}", description = "${serviceDescription}")
@Produces(MediaType.APPLICATION_JSON)
public class ${serviceName}Controller{

	private final Logger logger = LoggerFactory.getLogger(${serviceName}Controller.class);

	/**
	 * Static block to initialize the prometheus server
	 * 		* To expose End points
	 * */	
	static {
		PrometheusEndpoint.initializeHTTP();
		PrometheusEndpoint.registerEndpoints("${endPoint}");
	}
	
	/**
	 * Method to expose endpoint configuration with swagger documentation
	 * @GET
	 * @return String value
	 * @consumes APPLICATION_JSON
	 **/
	@ApiOperation(value = "End Point Description", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Data Retrived"),
	@ApiResponse(code = 401, message = "Authentication is Invalid"),
	@ApiResponse(code = 403, message = "Authorization is Invalid"),
	@ApiResponse(code = 404, message = "The requested resource is not found") })
	@GET
	@Path("/${endPoint}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String functionName1() {
		logger.info("Invoking /${endPoint} method");
		
		//TO-DO add your logic here
		
		//Incrementing the success/ request count of the end point hit
		numberOfRequests.labels("success").inc();
		return "Hello";
	}

}