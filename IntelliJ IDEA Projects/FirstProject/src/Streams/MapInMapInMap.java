package Streams;

import java.util.HashMap;
import java.util.Map;

public class MapInMapInMap
{
    public static void main(String[] args)
    {
        var map1 = new HashMap<>();
        var map2 = new HashMap<>();
        var map3 = new HashMap<>();

        map3.put("1", "value1");
        map3.put("2", "value2");
        map3.put("3", "value3");

        map2.put("nestedmap3", map3);
        map2.put("nestedmap3_copy", map3);

        map1.put("map2", map2);
        map1.put("map2-copy", map2);

        map1.entrySet().stream().flatMap(e -> ((HashMap<String, Map>) e.getValue()).entrySet().stream()).flatMap(e -> ((HashMap<String, String>) e.getValue()).entrySet().stream()).forEach(e -> {
            System.out.println(e.getKey() + " : " + e.getValue());
        });

        //        map1.entrySet().stream().flatMap(e -> ((HashMap<String, Map>) e.getValue()).entrySet().stream()).forEach(System.out::println);

        //        System.out.println(map1);
    }
}
