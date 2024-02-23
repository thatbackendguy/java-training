package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPractise
{
    public static void main(String[] args)
    {
        ArrayList<Integer> marks = new ArrayList<>();

        marks.add(10);
        marks.add(20);
        marks.add(100);
        marks.add(30);
        marks.add(60);
        marks.add(70);
        marks.add(40);
        marks.add(80);
        marks.add(90);
        marks.add(50);


        System.out.println(marks);

        List<Integer> sortedList = marks.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted List: " + sortedList);

        long noOfFailedStudents = marks.stream().filter(i -> i < 33).count();
        System.out.println("No. Of Failed Students: " + noOfFailedStudents);


    }
}
