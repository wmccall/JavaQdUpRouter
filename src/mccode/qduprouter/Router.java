package mccode.qduprouter;

import mccode.qduprouter.Listeners.ConnectListeners.HostConnectListener;
import mccode.qduprouter.Listeners.ConnectListeners.RequesterConnectListener;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Will on 5/6/2018.
 */

public class Router {
    public static int hosterPort = 16456;
    public static int requesterPort = 16455;

    public static HostConnectListener hostConnectListener;
    public static Thread hostConnectListenerThread;
    public static RequesterConnectListener requesterConnectListener;
    public static Thread requesterConnectListenerThread;

    public static void main(String[] args){
        startListeners();

        System.out.println("***  Router running.                 ***");
        System.out.println("***  Enter 'quit' to exit.           ***");
        String input = "";
        Scanner in = new Scanner(System.in);
        while(!input.equals("quit")){
            input = in.nextLine();
        }
        stopListeners();
        closeRouter();
    }

    static void startListeners()
    {
        hostConnectListener = new HostConnectListener();
        requesterConnectListener = new RequesterConnectListener();
        ListenHosts();
        ListenRequesters();

    }
    static void ListenHosts()
    {
        hostConnectListenerThread = new Thread(hostConnectListener);
        hostConnectListenerThread.start();
    }

    static void ListenRequesters()
    {
        requesterConnectListenerThread = new Thread(requesterConnectListener);
        requesterConnectListenerThread.start();
    }

    public static void closeRouter(){
        Iterator hostsIterator = Hosts.hosts.entrySet().iterator();
        while (hostsIterator.hasNext()) {
            Map.Entry hostKeyPair = (Map.Entry)hostsIterator.next();
            System.out.println("Host - " + hostKeyPair.getKey());
            Host exitingHost = (Host) hostKeyPair.getValue();
            try {
                exitingHost.close();
            } catch (IOException e) {
                //TODO handle IOException better
                e.printStackTrace();
            }
            hostsIterator.remove();
        }
    }

    public static void stopListeners(){
        try {
            hostConnectListener.terminate();
        } catch (IOException e) {
            System.out.print("HostConnectListener error:");
            e.printStackTrace();
        }
        try {
            requesterConnectListener.terminate();
        } catch (IOException e) {
            System.out.print("RequesterConnectListener error:");
            e.printStackTrace();
        }
    }
}

