package ca.krystasalera;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L1DevKrystaSaleraSpringBootApplication {

	static final Logger logger = LogManager.getLogger(L1DevKrystaSaleraSpringBootApplication.class.getName());
	 
	
	public static void main(String[] args) {
		logger.info("entered application");
		SpringApplication.run(L1DevKrystaSaleraSpringBootApplication.class, args);
	}
}
