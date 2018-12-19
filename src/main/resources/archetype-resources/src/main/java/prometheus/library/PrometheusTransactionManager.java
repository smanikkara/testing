package ${groupId}.prometheus.library;

import java.util.List;

/**
 * Transaction manager interface for prometheus library
 **/
public interface PrometheusTransactionManager {
	
	/**
	 *	Method to build prometheus connection object
	 * 	@param host
	 *  @param port
	 **/
	Connection getConnection(String host, int port);
	
	/**
	 *	Method to query prometheus
	 * 	@param String
	 *  @param long
	 *  @param long
	 *  @param long
	 *  @param Connection
	 **/
	String queryPrometheus(String promQl, Long startTime, Long endTime, Long step, Connection connection);

	/**
	 *	Method to parser the prometheus response to pojo object
	 * 	@param String
	 *  
	 **/
	List<PrometheusTimeSeriesData> parseAsPojo(String prmetheusResponse);

	/**
	 *	Method to parse/ convert prometheus pojo object to generic api response
	 * 	@param PrometheusTimeSeriesData
	 * 
	 **/
	Object processPojoToResult(PrometheusTimeSeriesData prometheusTimeSeriesData);

}
