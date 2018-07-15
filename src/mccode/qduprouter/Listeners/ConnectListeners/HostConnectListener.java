package mccode.qduprouter.Listeners.ConnectListeners;

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
                if(isTerminated){
                    System.out.println("HostConnectListener closing due to router closing");
                }else{
                    System.out.println("HostConnectListener closing due to unexpected error");
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
            System.out.println("HostConnectListener cannot bind to " + Router.hosterPort);
            e.printStackTrace();
        }
    }
}
