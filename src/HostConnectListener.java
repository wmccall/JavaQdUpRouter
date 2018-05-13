import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Will on 5/6/2018.
 */
public class HostConnectListener extends ConnectListener{
    @Override
    public void listen(){

        server = new ServerSocket(Router.hosterPort);
        while (true) {
            Hosts.add(new StreamIO(server.accept()));
        }
    }
}
