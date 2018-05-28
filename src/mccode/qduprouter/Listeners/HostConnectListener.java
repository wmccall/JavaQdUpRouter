package mccode.qduprouter.Listeners;

import mccode.qduprouter.Hosts;
import mccode.qduprouter.Router;
import mccode.qduprouter.StreamIO;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Will on 5/6/2018.
 */
public class HostConnectListener extends ConnectListener{

    @Override
    public void listen() throws IOException {
        server = new ServerSocket(Router.hosterPort);
        while (true) {
            try {
                Hosts.add(new StreamIO(server.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminate() throws IOException {
        server.close();
    }
}
