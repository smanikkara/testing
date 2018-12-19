# SPRING REST API ARCHETYPE

This is a custom maven archetype to generate Standard Spring boot application with REST (JAX-RS) implementation with following design components

	1) Ready to run Spring boot application
		i) Undertow as default web container
	2) Prometheus end points
	3) Prometheus response <--> JAVA object translation
	4) SWAGGER UI documentation
	5) JAX-RS standard implementation of REST services
	6) JTracer logging


## Libraries used

	1) Spring boot 2.1.1.RELEASE
	2) Apache CXF 3.2.5
	3) SWAGGER UI 3.19.4
	4) Prometheus Client 0.5.0
	5) JSON 20180130
	6) Amadeus JTracer 1.5.87


## Parameters for generating the Spring boot application

**Archetype parameters**

	1) ArchetypeVersion 		- Version of the custom archetype
	2) Group Id 	    		- Group Id of the custom archetype
	3) Archetype artifact Id	- Artifact name of the custom archetype

**Application parameters**

	1) Artifact Id		- Name of the Spring boot application
	2) Version		    - Version of the Spring boot application
	3) Context Path 	- Base path of the service
	4) Path			    - Base path of the controller
	5) Service Name 	- Name of the service
	6) Service Description  - Description of the service being built
	7) End Point		- End point name to be exposed


## Structure of Spring boot application

	src
          main
	    java
	      bootloader
	        Bootloader.java
	      config
	        SwaggerConfig.java
	      constants
	      controller
	        __ServiceName__Controller.java
	      dto
	      exception
	      prometheus
	      service
	      Utils
	      validation
	    resources
	      application.yml
	pom.xml
	logback.xml
	log.traceconf

## Steps to execute

**Make sure you have MAVEN installed/ configured in your local**

	1) Clone this package to your desired location

	2) cd java-microservices-rest-archetype

	3) mvn clean install

	4) cd ../

	5) run the following command

	mvn archetype:generate 	\
	-DarchetypeGroupId=com.amadeus.sentinel.api  \
	-DarchetypeArtifactId=spring-rest-api-archetype  \
	-DarchetypeVersion=1.0 \
	-DgroupId=<Group Id of application> 	\
	-DartifactId=<Name of the application>

	The maven generator will execute and it will switch to interactive mode to get other required (mandatory) parameters
	   
	   * Context Path
	   * Path
	   * Service Name
	   * Service Description
	   * End point

	6) Your Spring boot application will be generated

**JTracer logging**

	1) To run your spring boot microservice with JTracer follow the below steps
			a) Copy the logback.xml and log.traceconf to base directory of the jar generated(deployment directory)
			b) Update the logserver names (which ever is applicable for your case) in log.traceconf
			c) Run the spring boot jar with JVM arguement -DJTRACER_CONFIG=/absolute path of log.traceconf
			d) Check if the logs are traced to the respective log servers

**Swagger UI**
	
	The swagger UI is available in the following path:

	http://<hostname>:<port>/<contextPath>/api-docs?url=/<contextPath>/swagger.json#
	
**Prometheus end points**

	The spring boot service will by default prometheus end points which can be fed to prometheus/grafana for self monitoring
	
	The URL is http://<hostname>:<prometheusPort>/
		
		a) Here the prometheus port refers to port number of prometheus HTTP server
		b) This HTTP server is started when the spring boot application is run
		c) The default port number configured in application.yml is 5000 (This can be changed in applicaiton.yml) file
		
		
