package ${groupId}.prometheus.library;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ${groupId}.prometheus.library.Connection;
import ${groupId}.prometheus.library.PrometheusTimeSeriesData;

/**
 * Implementation of the prometheus Transaction manager interface for prometheus library
 **/
public class PrometheusTransactionManagerImpl implements PrometheusTransactionManager {

	private static final Logger logger = LogManager.getLogger(PrometheusTransactionManager.class);
	
	@Override
	public Connection getConnection(String host, int port) {
		return new Connection(host, port);
	}

	@Override
	public String queryPrometheus(String promQl, Long startTime, Long endTime, Long step, Connection connection) {

		PrometheusQuery prometheusQuery = new PrometheusQuery(promQl, startTime, endTime, step);
		logger.info("Initiating Prometheus Query: " + promQl);
		try {
			String prometheusResponse = prometheusQuery.execute(connection);
			logger.info("Query successful. Prometheus Response: " + prometheusResponse);
			return prometheusResponse;
		} catch (KeyManagementException e) {
			logger.error("Key Management Exception encountered. Querying Failed");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error("No Such Algorithm Exception encountered. Querying Failed.");
			e.printStackTrace();
		}

		return "There was an error in querying Prometheus. Please Retry";
	}

	@Override
	public List<PrometheusTimeSeriesData> parseAsPojo(String prmetheusResponse) {

		logger.info("Initiating translation to a POJO");
		List<PrometheusTimeSeriesData> prometheusTimeSeriesDataList = new ArrayList<>();
		prometheusTimeSeriesDataList = ParseStringToPojo.parseJsonResult(prmetheusResponse);
		if(prometheusTimeSeriesDataList!= null)
			logger.info("Transalation completed. POJO Object: " + prometheusTimeSeriesDataList);
		else
			logger.error("Translation failed. Null object returned");
		return prometheusTimeSeriesDataList;
	}

	@Override
	public Object processPojoToResult(PrometheusTimeSeriesData prometheusTimeSeriesData) {
		// TODO Auto-generated method stub
		return null;
	}



}
