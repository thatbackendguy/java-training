package SocketProgramming.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(3001);

            boolean stop = false;

            while(!stop)
            {
                System.out.println("Waiting for clients...");

                Socket clientSocket = serverSocket.accept();

                System.out.println("Client is connected!" + Thread.currentThread().getName());

                ClientThread clientThread = new ClientThread(clientSocket);

                clientThread.start();

            }
            serverSocket.close();

        } catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
