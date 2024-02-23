package fileio;
//package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IODemo
{
    public static void main(String[] args) throws Exception
    {
        // try with resources
        try (FileOutputStream fos = new FileOutputStream("/home/yash/JavaPractise/iodemo.txt"); FileInputStream fis = new FileInputStream("/home/yash/JavaPractise/iodemo.txt"))
        {

            // writing to a file
            String outStr = "My name is yash.";

            byte[] outByte = outStr.getBytes();

            fos.write(outByte);

            // reading a file
            byte[] inByte = new byte[fis.available()];

            fis.read(inByte);

            String inStr = new String(inByte);

            System.out.println(inStr);

        }

    }
}
