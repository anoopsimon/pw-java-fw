package core.cucumber;
import java.util.HashMap;

public class Context 
{
    private HashMap<String,Object> map;

    public Context()
    {
        map=new HashMap<String,Object>();
    }

    public void set(String key , Object value)
    {
        map.put(key, value);
    }

    
    public Object get(String key)
    {
        return map.get(key);
    }

    public <T> T get(String key,Class<T> clazz) {
        try {
            Object o = get(key);
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
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
