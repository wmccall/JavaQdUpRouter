import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Will on 5/6/2018.
 */
public abstract class ConnectListener {
    int clientId = 0;
    ServerSocket server;

    public abstract void listen() throws IOException;
}
