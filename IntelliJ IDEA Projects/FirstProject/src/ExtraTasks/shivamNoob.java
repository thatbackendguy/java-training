package ExtraTasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class shivamNoob
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> map = new HashMap<>();

        String filePath = "/home/yash/IdeaProjects/FirstProject/src/ExtraTasks/shivamNoob.txt";

        // Using StringBuilder to store the content of the file
        StringBuilder stringBuilder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        // Convert StringBuilder to String
        String fileContent = stringBuilder.toString();

        // Print the content of the file
        //        System.out.println(fileContent);

        String[] arr = fileContent.replaceAll("\\p{Punct}", "").replaceAll("\n", " ").split(" ");

        ArrayList<String> arrList = new ArrayList<>(List.of(arr));

        //System.out.println(arrList);

        for(String ele : arrList)
        {
            if(map.containsKey(ele))
            {
                int temp = map.get(ele) + 1;
                map.put(ele, temp);
            }
            else
            {
                map.put(ele, 1);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the entryList based on the values in descending order
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                // Compare the values in descending order
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Create a new LinkedHashMap to preserve the order of the sorted elements
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        // Copy the sorted entries from the entryList to the sortedMap
        for(Map.Entry<String, Integer> entry : entryList)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        // Print the sorted map
        System.out.println("Sorted Map (Descending Order): " + sortedMap);

    }
}
