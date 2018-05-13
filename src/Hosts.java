import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Will on 5/6/2018.
 */
public class Hosts {
    public static HashMap<String, Host> hosts = new HashMap<>();

    public static char[] alphanumeric = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789".toCharArray();
    public static Random r = new Random();

    public static boolean add(StreamIO streamIO) throws IOException {
        String hostMessage = streamIO.readLine();
        System.out.println("Host message: " + hostMessage);
        String key = "";
        if (hostMessage.equals("server:")){
            boolean unique = false;
            while (!unique){
                for(int i = 0; i < 6; i++){
                    key = key + alphanumeric[r.nextInt()*34];
                }
                if(hosts.get(key)==null){
                    unique = true;
                }
            }
            System.out.println("Host assigned the key: " + key);
            hosts.put(key, new Host(streamIO, key));
        }
    }
}
