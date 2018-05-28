package mccode.qduprouter;

import java.io.IOException;

/**
 * Created by Will on 5/6/2018.
 */
public class Requester extends User{

    Host host;

    public Requester(StreamIO streamIO, String key, Host host){
        super(streamIO,key);
        this.host = host;
    }

    public void close() throws IOException {
        streamIO.close();
    }

    public String read() throws IOException {
        return streamIO.readLine();
    }

    public void write(String message){
        streamIO.write(message);
    }

    public Host getHost(){
        return this.host;
    }
}
