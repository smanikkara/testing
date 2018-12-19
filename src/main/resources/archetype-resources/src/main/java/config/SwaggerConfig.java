package ${groupId}.config;

import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration
 * 
 * <p> 
 * This Swagger configuration class is used to define the
 * details of the swagger document and also the location of
 * swagger
 * spec: http://<host>:port/<contextRoot>/api-docs?url=/<contextPath>/swagger.json#
 * </p>
 * 
 * @author nkrishnamurthy
 *
 */
@Configuration
public class SwaggerConfig {

	@Value("${cxf.path}")
	  private String basePath;
	
	@Bean("swagger2Feature")
	  public Feature swagger2Feature() {
	    Swagger2Feature result = new Swagger2Feature();
	    result.setTitle("Spring Boot REST microservice");
	    result.setDescription("Standard MicroService REST services for APIs");
	    result.setBasePath(this.basePath);
	    result.setVersion("v1");
	    result.setContact("DMM-LOG-MON-BLR");
	    result.setSchemes(new String[] { "http", "https" });
	    result.setPrettyPrint(true);
	    result.setSupportSwaggerUi(true);
	    return result;
	  }
	
	
}
