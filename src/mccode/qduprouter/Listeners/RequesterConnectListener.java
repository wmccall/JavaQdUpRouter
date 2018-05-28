package mccode.qduprouter.Listeners;


import mccode.qduprouter.*;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Will on 5/6/2018.
 */
public class RequesterConnectListener extends ConnectListener {
    @Override
    public void listen() throws IOException {

        server = new ServerSocket(Router.requesterPort);

        while (true) {
            try {
                StreamIO newPotentialRequester = new StreamIO(server.accept());
                String key = getKey(newPotentialRequester);
                Host foundHost = findProperHost(key);
                foundHost.addRequester(newPotentialRequester);
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

    public Host findProperHost(String key){
        return Hosts.getHost(key);
    }

    public String getKey(StreamIO streamIO) throws IOException {
        return streamIO.readLine();
    }
}
