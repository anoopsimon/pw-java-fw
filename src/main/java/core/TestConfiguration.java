package core;

import java.util.HashMap;

public class TestConfiguration
 {
    public static HashMap configJson ()
    {
        return JsonUtil.fromJsonFile(System.getProperty("user.dir")+"/testconfig.json", HashMap.class);
    }
    public static boolean headless() {
        return  (boolean) configJson().get("headless");
       
    } 
    
}
