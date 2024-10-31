package pl.marcelkuczek.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the REST API.
 * This class configures and launches the Spring Boot application.
 *
 * @author Marcel Kuczek
 */
@SpringBootApplication
public class RestapiApplication {

	/**
	 * The main method which serves as the entry point for the Spring Boot application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}
}
