package ${groupId}.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Config file to set prometheus property values for static variables
 * The properties are defined in the application.yml file
 * */
@Configuration
public class PrometheusConfig {

	public static int port;
	
	@Value("${prometheus.port}")
	public void setPort(int port) {
		this.port = port;
	}
	
}
