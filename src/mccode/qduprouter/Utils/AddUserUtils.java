package mccode.qduprouter.Utils;

import java.util.HashMap;
import java.util.Random;


public class AddUserUtils {

    public static char[] alphanumeric = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789".toCharArray();
    public static Random r = new Random();

    public static String generateRandomKey(){
        String key = "";
        for(int i = 0; i < 6; i++){
            key += "" + alphanumeric[r.nextInt()*33];
        }
        System.out.println(key);
        return key;
    }

    public static String generateValidKey(HashMap map){
        String key = "";
        boolean unique = false;
        while (!unique){
            key = generateRandomKey();
            if(map.get(key)==null){
                unique = true;
            }
        }
        return key;
    }

}
