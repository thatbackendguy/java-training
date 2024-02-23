package DSA.Hashing;

import java.util.HashSet;

public class UnionOf2ArraysTask
{
    public static void UnionOf2Arrays(int[] a, int[] b)
    {
        HashSet<Integer> set = new HashSet<>();
        for(int x : a)
        {
            set.add(x);
        }
        for(int y : b)
        {
            set.add(y);
        }
        System.out.println(set);
    }

    public static void main(String[] args)
    {
        int[] a = {7, 3, 9};
        int[] b = {6, 3, 9, 2, 9, 4};

        UnionOf2Arrays(a, b);
    }
}
