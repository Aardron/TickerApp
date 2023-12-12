package cloudcode.Ticker;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** This class serves as an entry point for the Spring Boot app. */
@SpringBootApplication
public class TickerApp {

  private static final Logger logger = LoggerFactory.getLogger(TickerApp.class);

  public static void main(final String[] args) throws Exception {
    String port = System.getenv("PORT");
    if (port == null) {
      port = "8080";
      logger.warn("$PORT environment variable not set, defaulting to 8080");
    }

    SpringApplication app = new SpringApplication(TickerApp.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", port));


    app.run(args);
    logger.info("The container started successfully and is listening for HTTP requests on " + port);
  }
}
