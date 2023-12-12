package cloudcode.Ticker.store;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

interface Iconnector {
    long getTick() throws Exception;
}


public class Connector implements Iconnector {
    private final Firestore db;

    public Connector(Firestore db) {
        this.db = db;
    }

    public long getTick() throws Exception {
        DocumentReference ref = db.collection("tickers").document("ticker");
        ApiFuture<Long> val =
                db.runTransaction(
                        transaction -> {
                            DocumentSnapshot snapshot = transaction.get(ref).get();
                            Long tickval = snapshot.getLong("ticker") ;

                            final ApiFuture<WriteResult> updateFuture =
                                    ref.update("ticker", FieldValue.increment(1));

                            return tickval;
                        });

        return val.get();
    }
}
