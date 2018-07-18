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
                System.out.println("Message from requester: " + message);
                if(message == null){
                    System.out.println("Requester: " + requester.getKey() + " lost connection");
                    return;
                }
                else if(message.equals("Quit\n")){
                    System.out.println("Requester: " + requester.getKey() + " Quit");
                    break;
                }
                else if(message.equals("Ping\n")){

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
            System.out.print("Error while removing requester from message litener:");
            e.printStackTrace();
        }
    }
}
