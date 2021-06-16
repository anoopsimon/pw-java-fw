package core.cucumber;
import java.util.HashMap;

public class ScenarioContext 
{
    private HashMap<String,Object> map;

    public ScenarioContext()
    {
        map=new HashMap<String,Object>();
    }

    public void set(String key , Object value)
    {
        map.put(key, value);
    }

    public void get(String key)
    {
        map.get(key);
    }

 

    public void update(String key , Object value)
    {
        map.remove(key, value);
        map.put(key, value);
    }

    public void remove(String key , String value)
    {
        map.remove(key);
    }
    
}
