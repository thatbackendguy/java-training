package Streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NestedMapStream
{
    public static void main(String[] args)
    {
        Map<String, Map<String, String>> mapOfMaps = new HashMap<>();

        Map<String, String> innerMap1 = new HashMap<>();

        innerMap1.put("innerKey1", "innerValue1");

        innerMap1.put("innerKey2", "innerValue2");

        mapOfMaps.put("outerKey1", innerMap1);

        Map<String, String> innerMap2 = new HashMap<>();

        innerMap2.put("innerKey3", "innerValue3");

        innerMap2.put("innerKey4", "innerValue4");

        mapOfMaps.put("outerKey2", innerMap2);

        Map<String, Map<String, String>> filteredMapOfMaps = mapOfMaps.entrySet().stream().filter(entry -> entry.getKey().startsWith("outerKey1")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(filteredMapOfMaps);
    }
}
