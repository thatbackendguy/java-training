package DSA.Hashing;

import java.util.HashSet;
import java.util.Iterator;

class Names
{
    static int counter = 0;

    private String name;

    Names(String name)
    {
        this.name = name;
    }

    //    public boolean equals(Object obj)
    //    {
    //        if(this == obj)
    //            return true;
    //        return false;
    //    }


    @Override
    public int hashCode()
    {

        counter++;
        return counter;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}

public class hashSetDemo
{
    public static void main(String[] args)
    {
        HashSet<Names> mySet = new HashSet<>();

        Names s1 = new Names("yash");
        Names s2 = new Names("yash");

        mySet.add(s1);
        mySet.add(s2);


        Iterator<Names> itr = mySet.iterator();

        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }

        System.out.println("Size of HashSet: " + mySet.size());

    }
}
