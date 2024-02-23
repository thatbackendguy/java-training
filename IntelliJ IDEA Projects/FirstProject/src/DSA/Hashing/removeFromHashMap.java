package DSA.Hashing;

import java.util.HashMap;
import java.util.Iterator;

public class removeFromHashMap
{
    public static void main(String[] args)
    {
        HashMap<Integer, Integer> hm = new HashMap<>();

        hm.put(1, 10);
        hm.put(2, 20);
        hm.put(3, 30);
        hm.put(4, 40);
        hm.put(5, 50);

        Iterator<Integer> itr = hm.keySet().iterator();

        while(itr.hasNext())
        {
            if(itr.next() == 3)
            {
                itr.remove();
            }
        }

        System.out.println(hm);

    }
}
