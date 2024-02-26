package DSA;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.PriorityQueue;

enum Days
{
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumMapExample
{
    public static void main(String[] args)
    {
        EnumMap<Days, String> schedule = new EnumMap<>(Days.class);

        //        // Adding elements to the EnumMap
        schedule.putIfAbsent(Days.MONDAY, "Work");
        schedule.putIfAbsent(Days.TUESDAY, "Work");
        schedule.putIfAbsent(Days.WEDNESDAY, "Study");
        schedule.putIfAbsent(Days.THURSDAY, "Study");
        //        schedule.computeIfPresent(Days.FRIDAY, (key,value)-> value.("shivam"));

        // Getting elements from the EnumMap
        System.out.println(schedule); // Output: Relax

        PriorityQueue<Integer> pq = new PriorityQueue<>();


        pq.add(67);
        pq.add(99);
        pq.add(89);
        pq.add(45);
        pq.add(100);
        pq.add(12);
        System.out.println(pq);
    }
}
