package ${groupId}.bootloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ${groupId}.bootloader.Bootloader;

/**
 * This is a typical boostrap class for Spring Boot based application
 *  
 * @author nkrishnamurthy
 *
 */
@SpringBootApplication
@ComponentScan("${groupId}")
public class Bootloader{

	private static final Logger logger = LoggerFactory.getLogger(Bootloader.class);

	public static void main(String[] args) {
		
		logger.info("Inside the spring boot API");
		
		SpringApplication.run(Bootloader.class, args);
	}
	
}
