package SocketProgramming.ChatApp;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args)
    {
        //        Scanner sc = new Scanner(System.in);
        try
        {
            ServerSocket serverSocket = new ServerSocket(12345);

            Socket socket = serverSocket.accept();

            System.out.println("User connected!");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            //            writer.println("Connected to server!");
            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true)
            {

                System.out.print("(Server) Enter message to send: ");

                String msg = terminalReader.readLine();

                writer.println(msg);

                String clientInput = reader.readLine();

                if(!clientInput.isEmpty())
                    System.out.println("(Client): " + clientInput);

                if(clientInput.equals("exit"))
                {
                    break;
                }
            }

        } catch(IOException e)

        {
            throw new RuntimeException(e);
        }
    }
}
