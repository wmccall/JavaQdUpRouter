package mccode.qduprouter.Listeners.ConnectListeners;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Will on 5/6/2018.
 */
public abstract class ConnectListener implements Runnable{
    int clientCount = 0;
    boolean isTerminated = false;
    ServerSocket server;

    public abstract void listen() throws IOException;

    public void terminate() throws IOException {
        isTerminated = true;
        server.close();
    }
}
