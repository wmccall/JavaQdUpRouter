package mccode.qduprouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Will on 5/6/2018.
 */
public class StreamIO{
    Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public StreamIO(Socket socket) throws IOException {
        this.socket = socket;
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    public void write(String message){
        System.out.println("StreamIO: Writing: " + message);
        printWriter.println(message);
    }

    public void close() throws IOException {
        bufferedReader.close();
        printWriter.close();
        socket.close();
    }
}
