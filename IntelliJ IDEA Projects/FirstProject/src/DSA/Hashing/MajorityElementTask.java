package DSA.Hashing;

import java.util.HashMap;

public class MajorityElementTask
{
    public static void MajorityElement(int[] arr)
    {
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        int threshold = arr.length / 3;
        if(threshold != 0)
        {
            for(int a : arr)
            {
                if(numFreq.containsKey(a))
                {
                    int freq = numFreq.get(a) + 1;
                    numFreq.put(a, freq);
                }
                else
                {
                    numFreq.put(a, 1);
                }
            }
            for(int a : numFreq.keySet())
            {
                if(numFreq.get(a) > threshold)
                {
                    System.out.print(a + " ");
                }
            }
        }

    }

    public static void main(String[] args)
    {

        //        int[] arr = {1, 3, 2, 5, 1, 3, 1, 5, 1};
        int[] arr = {1, 3, 3, 3, 1, 3, 1, 3, 1};
        //int[] arr = {1, 2};
        MajorityElementTask.MajorityElement(arr);
    }
}
