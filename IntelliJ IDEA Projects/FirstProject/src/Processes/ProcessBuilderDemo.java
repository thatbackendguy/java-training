package Processes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderDemo
{

    public static void main(String[] args) throws IOException
    {
        Process process = Runtime.getRuntime().exec("fping -s 10.20.40.239");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        output.lines().forEach(System.out::println);
        //        output.lines().filter(s -> s.contains("context")).forEach(System.out::println);


        //        process = Runtime.getRuntime().exec("nmap 10.20.40.227");
        //        output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //        output.lines().forEach(System.out::println);

        //        process = Runtime.getRuntime().exec("ssh shivam@10.20.40.239");
        //        output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //        output.lines().forEach(System.out::println);

        //        process = Runtime.getRuntime().exec("whoami");
        //        output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //        output.lines().forEach(System.out::println);

        //                ProcessBuilder builder = new ProcessBuilder("code");
        //                Process editProcess = builder.start();
        //                output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //                output.lines().forEach(System.out::println);

    }


}
