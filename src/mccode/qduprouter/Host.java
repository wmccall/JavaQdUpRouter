package mccode.qduprouter;

import java.io.IOException;

/**
 * Created by Will on 5/6/2018.
 */
public class Host extends User{
    Requesters requesters;

    public Host(StreamIO streamIO, String key){
        super(streamIO, key);
        requesters = new Requesters();
    }

    public void addRequester(StreamIO streamIO){
        requesters.add(streamIO, this);
    }

    public void close() throws IOException {
        requesters.close();
        streamIO.close();
    }

    public Requesters getRequesters(){
        return this.requesters;
    }

    public void removeRequester(String key) throws IOException {
        requesters.remove(key);
    }

    public void printRequesters(){
        requesters.requesters.forEach((k, v) -> {
            System.out.println("Requester: " + k);
        });
    }
}
