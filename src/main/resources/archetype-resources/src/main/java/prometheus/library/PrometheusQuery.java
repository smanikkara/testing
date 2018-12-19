package ${groupId}.prometheus.library;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ${groupId}.prometheus.library.Connection;

/**
 * Utility to build and execute prometheus query and bypass the https
 * certificate
 **/
public class PrometheusQuery {
	private String promQl;
	private Long startTime;
	private Long endTime;
	private Long step;

	public PrometheusQuery(String promQl, Long startTime, Long endTime, Long step) {
		this.promQl = promQl;
		this.startTime = startTime;
		this.endTime = endTime;
		this.step = step;
	}

	public String getPromQl() {
		return promQl;
	}

	public void setPromQl(String promQl) {
		this.promQl = promQl;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	/**
	 * Method to execute the prometheus query
	 * 
	 * @param Connection
	 * @return String
	 * @throws NoSuchAlgorithmException,
	 *             KeyManagementException
	 **/
	public String execute(Connection connection) throws NoSuchAlgorithmException, KeyManagementException {
		String host = connection.getHostName();
		int port = connection.getPortNumber();

		// disable the https for prometheus
		disableValidCertificateVerfifcation();

		StringBuilder restUrl = createRestUrl(connection);
		RestTemplate restTemplate = new RestTemplate();
		String promQl = this.promQl;
		String result = restTemplate.getForObject(restUrl.toString(), String.class, promQl);
		return result;
	}

	/**
	 * Method to build the prometheus query url
	 * 
	 * @param Connection
	 * @return StringBuilder
	 * 
	 **/
	private StringBuilder createRestUrl(Connection connection) {

		String host = connection.getHostName();
		int port = connection.getPortNumber();

		StringBuilder restUrl = new StringBuilder(host);

		// Append Port
		if (port != 0) {
			restUrl.append(":" + port + "/");
		} else {
			restUrl.append("/");
		}

		// Append Root Context
		restUrl.append("api/v1/");

		// Append Type of Query
		if (this.endTime != null) {
			restUrl.append("query_range");
		} else {
			restUrl.append("query");
		}

		// Add Query
		restUrl.append("?query={promQl}");

		// Add Start and End time
		if (this.endTime == null && this.startTime != null) {
			restUrl.append("&time=" + this.startTime);
		} else if (startTime != null) {
			restUrl.append("&start=" + startTime + "&end=" + endTime);
		}

		// Add Step
		if (step != null) {
			restUrl.append("&step=" + this.step);
		}

		// Log resultant query
		System.out.println(restUrl.toString());

		return restUrl;
	}

	/**
	 * Method to bypass the https certificate validation for prometheus url
	 * 
	 * @throws NoSuchAlgorithmException, KeyManagementException
	 **/
	private void disableValidCertificateVerfifcation() throws NoSuchAlgorithmException, KeyManagementException {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };
		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {

	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
};
// Install the all-trusting host verifier
HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);}}
