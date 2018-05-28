package mccode.qduprouter;

import java.io.IOException;

public class User {
    StreamIO streamIO;
    String key;

    public User(StreamIO streamIO, String key){
        this.streamIO = streamIO;
        this.key = key;
    }

    public String read() throws IOException {
        return streamIO.readLine();
    }

    public void write(String message){
        streamIO.write(message);
    }

    public String getKey(){
        return key;
    }
}
