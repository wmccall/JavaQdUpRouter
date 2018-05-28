package mccode.qduprouter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Will on 5/6/2018.
 */
public class Requesters {
    public HashMap<String, Requester> requesters;
    private int hostCount = 0;
    String connectPipe = "╠";
    String endPipe = "╚";

    public Requesters(){
        requesters = new HashMap<>();
    }

    public void add(StreamIO streamIO){
        hostCount++;
    }

    public void close() throws IOException {
        Iterator requestersIterator = requesters.entrySet().iterator();
        int closeCount = 0;
        while (requestersIterator.hasNext()) {
            Map.Entry requesterKeyPair = (Map.Entry)requestersIterator.next();
            closeCount++;
            System.out.println("       " + (closeCount==hostCount ? endPipe : connectPipe) + " Requester - " + requesterKeyPair.getKey());
            Requester exitingRequester = (Requester) requesterKeyPair.getValue();
            exitingRequester.close();
            requestersIterator.remove();

        }
    }
}
