import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Will on 5/6/2018.
 */
public class StreamIO{
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public StreamIO(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    public void write(String message){
        out.write(message);
    }
}
