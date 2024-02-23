package DSA.Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class hashMapDemo
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("India", 1);
        map.put("USA", 2);
        map.put("UAE", 3);
        map.put("Canada", 4);

        map.put("USA", 9);

        if(map.containsKey("Vietnam"))
        {
            System.out.println("Key is present");
        }
        else
        {
            System.out.println("Key is not present");
        }

        System.out.println(map);
        System.out.println("Value of India: " + map.get("India"));

        for(Map.Entry<String, Integer> ele : map.entrySet())
        {
            System.out.println(ele.getKey());
            System.out.println(ele.getValue());
        }

        Set<String> keys = map.keySet();
        for(String key : keys)
        {
            System.out.println(key + ":" + map.get(key));
        }

        map.remove("UAE");
        System.out.println(map);
    }
}
