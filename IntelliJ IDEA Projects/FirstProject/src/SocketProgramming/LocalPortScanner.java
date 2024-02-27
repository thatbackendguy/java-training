package SocketProgramming;


import java.io.IOException;
import java.net.ServerSocket;

public class LocalPortScanner
{
    public static void main(String[] args)
    {
        int port = 1000;
        while(port < 65536)
        {
            try
            {
                ServerSocket ss = new ServerSocket(port);

            } catch(IOException e)
            {
                System.out.println("Port " + port + " is already open!");

            }
            port++;
        }
    }
}
