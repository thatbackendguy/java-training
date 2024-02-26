package Streams;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTask
{
    public static void main(String[] args)
    {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        ArrayList<Integer> myList = new ArrayList<>();

        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(10);

        for(int i = 1; i <= 10; i++)
        {
            map.put(i, myList);
        }

        var res = map.entrySet().stream().filter(e -> e.getKey() % 2 == 0).collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().filter(ele -> ele % 2 == 1).collect(Collectors.toList())));

        System.out.println(res);
    }
}
