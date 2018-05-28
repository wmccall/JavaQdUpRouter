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
        requesters.add(streamIO);
    }

    public void close() throws IOException {
        requesters.close();
        streamIO.close();
    }

}
