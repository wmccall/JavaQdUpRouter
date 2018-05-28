package mccode.qduprouter.Listeners.MessageListeners;

import mccode.qduprouter.Requester;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Will on 5/28/2018.
 */
public class RequesterMessageListener implements Runnable{
    Requester requester;

    public RequesterMessageListener(Requester requester) {
        this.requester = requester;
    }

    @Override
    public void run() {
        String message;
        while(true)
        {
            try
            {
                message = requester.read();
                if(message == "Quit\n"){
                    System.out.println("Requester: " + requester.getKey() + " Quit");
                    break;
                }
                if(message == null){
                    System.out.println("Requester: " + requester.getKey() + " lost connection");
                    return;
                }
                if(message == "Ping\n"){

                }
                else
                {
                    System.out.println("Message received from Requester: " + requester.getKey() + " - " + message);
                }
            }
            catch(IOException e)
            {
                System.out.println("Requester: " + requester.getKey() + " - Server Error: 1");
                return;
            }
            System.out.println("Message sending to host");
            requester.getHost().write(message);
        }

        // Remove current server from the list
        try {
            requester.getHost().removeRequester(requester.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
