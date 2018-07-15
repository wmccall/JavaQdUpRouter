package mccode.qduprouter.Listeners.ConnectListeners;


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
                if(isTerminated){
                    System.out.println("RequestConnectListener closing due to router closing");
                }else{
                    System.out.println("RequestConnectListener closing due to unexpected error");
                    e.printStackTrace();
                }

            }catch (NullPointerException e){

            }
        }
    }

    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            System.out.println("RequesterConnectListener cannot bind to " + Router.requesterPort);
            e.printStackTrace();
        }
    }

    public Host findProperHost(String key){
        return Hosts.getHost(key);
    }

    public String getKey(StreamIO streamIO) throws IOException {
        return streamIO.readLine();
    }
}
