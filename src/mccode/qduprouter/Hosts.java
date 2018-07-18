package mccode.qduprouter;

import mccode.qduprouter.Listeners.MessageListeners.HostMessageListener;

import java.io.IOException;
import java.util.HashMap;

import static mccode.qduprouter.Utils.AddUserUtils.generateValidKey;

/**
 * Created by Will on 5/6/2018.
 */
public class Hosts{
    public static HashMap<String, Host> hosts = new HashMap<>();

    public static boolean add(StreamIO streamIO) throws IOException {
        String hostMessage = streamIO.readLine();
        System.out.println("Host message: " + hostMessage);
        String key = "";
        if (hostMessage.equals("server:")){
            key = generateValidKey(hosts);
            Host newHost = new Host(streamIO, key);
            System.out.println("Host assigned the key: " + key);
            hosts.put(key, newHost);
            newHost.write(key);
            new Thread(new HostMessageListener(newHost)).start();
            return true;
        }
        return false;
    }

    public static void printHosts(){
        hosts.forEach((k, v) -> System.out.println("Host: " + k));
    }

    public static Host getHost(String key){
        return hosts.get(key);
    }

    public static void removeHost(String key) throws IOException {
        hosts.remove(key).close();
    }
}
