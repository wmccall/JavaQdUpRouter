package mccode.qduprouter.Listeners.MessageListeners;

import mccode.qduprouter.Host;
import mccode.qduprouter.Hosts;
import mccode.qduprouter.Requester;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Will on 5/28/2018.
 */
public class HostMessageListener implements Runnable{
    Host host;

    public HostMessageListener(Host host) {
        this.host = host;
    }

    @Override
    public void run() {
        String message;
        while(true)
        {
            try
            {
                message = host.read();
                if(message == "Quit\n"){
                    System.out.println("Host: " + host.getKey() + " Quit");
                    break;
                }
                if(message == null){
                    System.out.println("Host: " + host.getKey() + " lost connection");
                    return;
                }
                if(message == "Ping\n"){

                }
                else
                {
                    System.out.println("Message received from Host: " + host.getKey() + " - " + message);
                }
            }
            catch(IOException e)
            {
                System.out.println("Host: " + host.getKey() + " - Server Error: 1");
                return;
            }
            System.out.println("Message sending to clients");

            Iterator requestersIterator = host.getRequesters().getRequestersHashMap().entrySet().iterator();
            while (requestersIterator.hasNext()) {
                Map.Entry requesterKeyPair = (Map.Entry) requestersIterator.next();
                System.out.println("Sending message from host: " + host.getKey() + " to requester: " + requesterKeyPair.getKey());
                ((Requester)requesterKeyPair.getValue()).write(message);
            }
        }

        // Remove current host from the list
        try {
            Hosts.removeHost(host.getKey());
        } catch (IOException e) {
            System.out.print("Error while removing host from message listener:");
            e.printStackTrace();
        }
    }
}
