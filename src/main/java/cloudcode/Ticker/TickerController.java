package cloudcode.Ticker;

import cloudcode.Ticker.store.Connector;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Defines a controller to handle HTTP requests
 */
@RestController
public final class TickerController {

    private static final Logger logger = LoggerFactory.getLogger(TickerController.class);

    private Connector conn;

    public TickerController() {
        try {
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("glowing-service-407613")
                            .setCredentials(GoogleCredentials.getApplicationDefault())
                            .build();


            Firestore db = firestoreOptions.getService();

            this.conn = new Connector(db);

        } catch (IOException e) {
            logger.warn("error occurred while creating firestore");
        }
    }


    @GetMapping("/tick")
    public Long getUniqueTicker() {
        long val = 0L;

        try {
            val = conn.getTick();
        } catch (Exception e) {
            logger.warn("error occurred while creating firestore");
        }

        return val;
    }

}
