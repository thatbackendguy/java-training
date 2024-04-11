import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapDemo
{
    public static void main(String[] args)
    {
        var outerMap = new ConcurrentHashMap<Integer, HashMap>();

        var innerMap = new HashMap<Integer, Integer>();

        innerMap.put(1, 10);
        innerMap.put(2, 20);
        innerMap.put(3, 30);
        innerMap.put(4, 40);
        innerMap.put(5, 50);

        outerMap.put(1, innerMap);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                    for(int i=0; i<10000; i++)
                    {
                        innerMap.put(i,i*2);
                    }
            }
        }).start();

        outerMap.put(2,innerMap);

        while(true)
        {
            for(var entry : outerMap.entrySet())
            {
                for(var val: entry.getValue().entrySet())
                {
                    System.out.println(entry.getKey() + ":" + val);
                }
                System.out.println(entry);
            }
        }
    }
}
