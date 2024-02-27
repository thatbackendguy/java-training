package SocketProgramming.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(3000);

            System.out.println("Waiting for clients...");

            Socket socket = serverSocket.accept();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Hello Client!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientInput = input.readLine();

            System.out.println(clientInput);

            input.close();

            out.close();

            socket.close();
        } catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
