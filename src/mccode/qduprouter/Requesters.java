package mccode.qduprouter;

import mccode.qduprouter.Listeners.MessageListeners.RequesterMessageListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static mccode.qduprouter.Utils.AddUserUtils.generateValidKey;

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

    public void add(StreamIO streamIO, Host host){
        hostCount++;
        String key = generateValidKey(requesters);
        Requester newRequester = new Requester(streamIO, key, host);
        requesters.put(key, newRequester);
        new Thread(new RequesterMessageListener(newRequester)).start();
    }

    public void remove(String key) throws IOException {
        requesters.remove(key).close();
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

    public HashMap getRequestersHashMap(){
        return requesters;
    }
}
