package ExtraTasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordFrequency
{

    public static void main(String[] args) throws IOException
    {
        String filePath = "/home/yash/IdeaProjects/FirstProject/src/ExtraTasks/shivamNoob.txt";

        // Read all lines from the file and split them into words
        List<String> words = Files.lines(Paths.get(filePath)).flatMap(line -> Arrays.stream(line.split("\\s+"))).map(String::toLowerCase).collect(Collectors.toList());

        // Create a map with words as keys and their frequencies as values
        Map<String, Long> wordFrequencyMap = words.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        // Find the word with the maximum frequency
        Optional<Map.Entry<String, Long>> maxEntry = wordFrequencyMap.entrySet().stream().max(Map.Entry.comparingByValue());

        // Output the word with the maximum frequency
        maxEntry.ifPresent(entry -> {
            System.out.println("The word with the maximum frequency is: " + entry.getKey() + ", with frequency: " + entry.getValue());
        });
    }
}

