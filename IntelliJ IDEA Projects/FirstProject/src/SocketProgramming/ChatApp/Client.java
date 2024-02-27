package SocketProgramming.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            System.out.println("Server IP Address: " + serverAddress.getHostAddress());

            Socket socket = new Socket("localhost", 12345);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true)
            {
                System.out.println("(Server): " + reader.readLine());

                System.out.print("(Client) Enter message to send: ");

                String msg = sc.next();

                writer.println(msg);

                if(msg.equals("exit"))
                    break;

            }
            reader.close();
            writer.close();
            socket.close();
            sc.close();

        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
