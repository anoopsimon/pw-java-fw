package core;

import java.util.HashMap;

import core.util.JsonUtil;

public class TestConfiguration
 {
    public static HashMap configJson ()
    {
        return JsonUtil.fromJsonFile(System.getProperty("user.dir")+"/testconfig.json", HashMap.class);
    }
    public static boolean headless() {
        return  (boolean) configJson().get("headless");
       
    } 
    public static String appUrl() {
        return configJson().get("appUrl").toString();
       
    } 
    
}
