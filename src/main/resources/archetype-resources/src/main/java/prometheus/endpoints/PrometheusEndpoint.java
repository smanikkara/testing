package ${groupId}.prometheus.endpoints;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.Counter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ${groupId}.prometheus.constants.PrometheusConstants.TOTAL_REQUESTS;
import static ${groupId}.prometheus.constants.PrometheusConstants.SUCCESSFUL_HITS;
import static ${groupId}.prometheus.constants.PrometheusConstants.FAILED_HITS;
import static ${groupId}.prometheus.constants.PrometheusConstants.TOTAL_SUCCESSFUL_HITS;
import static ${groupId}.prometheus.constants.PrometheusConstants.TOTAL_FAILED_HITS;
import static ${groupId}.prometheus.constants.PrometheusConstants.TOTAL_API_HITS;

import static ${groupId}.config.PrometheusConfig.port;


/**
 * Prometheus class to register the self monitored metrics with prometheus server
 * Monitored metrics:
 * 		1) Number of Requests
 * 		2) Number of Successful hits
 * 		3) Number of failures
 **/
public class PrometheusEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(PrometheusEndpoint.class);

	public static HTTPServer server;
	public static Counter numberOfRequests;
	public static Counter successCount;
	public static Counter failureCount;
	
	/**
	 * Method to initialize the prometheus HTTP server to expose end points
	 * 
	 * http://<hostname>:<${prometheus.port}>/metrics
	 * 
	 * */	
	public static void initializeHTTP() {
		try {
			logger.info("Initializing the prometheus HTTP server with port ${prometheus.port}");
			server = new HTTPServer(port);
			logger.info("Prometheus HTTP server initialization complete!!");
		} catch (IOException exception) {
			logger.error("Exception with initializing the prometheus server", exception);
		}
	}
	
	/**
	 *	Method to register the metrics for every hit to the service 
	 * 	@param API service end point name
	 **/
	public static void registerEndpoints(String endPoint) {

		logger.info("Registering endpoint :'\'" + endPoint + "with the default metrics");

		numberOfRequests = Counter.build().name(TOTAL_REQUESTS).help(TOTAL_API_HITS)
				.labelNames(endPoint).register();
		
		successCount = Counter.build().name(SUCCESSFUL_HITS).help(TOTAL_SUCCESSFUL_HITS)
				.labelNames(endPoint).register();
		
		failureCount = Counter.build().name(FAILED_HITS).help(TOTAL_FAILED_HITS)
				.labelNames(endPoint).register();
	}

}
