package mccode.qduprouter;

import java.io.IOException;

/**
 * Created by Will on 5/6/2018.
 */
public class Requester extends User{


    public Requester(StreamIO streamIO, String key){
        super(streamIO,key);
    }

    public void close() throws IOException {
        streamIO.close();
    }
}
