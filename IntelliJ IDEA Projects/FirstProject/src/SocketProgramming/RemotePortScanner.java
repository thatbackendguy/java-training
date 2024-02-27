package SocketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RemotePortScanner
{
    public static void main(String[] args)
    {
        InputStreamReader in = new InputStreamReader(System.in);

        BufferedReader reader = new BufferedReader(in);

        String targetIP = "";

        int fromPort = 0;

        int toPort = 0;

        System.out.print("Enter IP address: ");

        try
        {
            targetIP = reader.readLine();
        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        boolean isValid = false;

        while(!isValid)
        {
            try
            {
                System.out.print("Enter the first port: ");

                String portString = reader.readLine();

                fromPort = Integer.parseInt(portString);

                if(fromPort >= 0 && fromPort <= 65536)
                {
                    isValid = true;
                }
                else
                    System.out.println("Invalid port! Port range = [1,65536]");
            } catch(NumberFormatException nFE)
            {
                System.out.println("Please enter a number!");
            } catch(IOException e)
            {
                throw new RuntimeException(e);
            }

            int port = fromPort;
            while(port >= fromPort && port <= fromPort)
            {
                try
                {
                    Socket socket = new Socket(targetIP, port);
                    System.out.println("Port " + port + " is in listening state!");
                    socket.close();
                } catch(UnknownHostException uHE)
                {
                    System.out.println("UnknownHostException: " + uHE);
                } catch(IOException ioE)
                {
                    System.out.println("IOException: " + ioE);
                } catch(IllegalArgumentException iAE)
                {
                    System.out.println("IllegalArgumentException: " + iAE);
                } catch(Exception e)
                {
                    System.out.println("Exception: " + e);
                }
                port++;
            }
        }
    }
}
