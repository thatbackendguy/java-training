package SocketProgramming.MachhliGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

class ClientThread extends Thread
{
    Scanner sc = new Scanner(System.in);

    private final Socket socket;

    private PrintWriter writer;

    ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true)
            {
                String clientMsg = reader.readLine();

                if(!clientMsg.equals(""))
                {
                    if(MultiClientServer.checkMsg(clientMsg))
                        MultiClientServer.broadcast(clientMsg, this);
                    else
                    {
                        MultiClientServer.broadcast("is out!\nGame is reset!", this);
                        break;
                    }
                }

            }
            socket.close();
        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message)
    {
        writer.println(message);
    }
}

public class MultiClientServer
{
    static int counter = 0;

    public static boolean checkMsg(String message)
    {
        String[] gameTerms = {"m", "pmg", "cpk", "m", "m", "pmg", "pmg", "cpk", "cpk", "m", "m", "m", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "m", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "pmg", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk", "cpk"};

        System.out.println(message + " : " + gameTerms[counter] + Thread.currentThread().getName());

        if(gameTerms[counter].equals(message))
        {
            counter++;
            System.out.println(counter);
            System.out.println("match");
            return true;
        }
        counter = 0;
        return false;
    }

    public static void broadcast(String message, ClientThread curr)
    {
        for(var client : clientMap.keySet())
        {
            if(curr != client)
                client.sendMessage(clientMap.get(curr) + ": " + message);
            else if(message.equals("is out!\nGame is reset!"))
                client.sendMessage("You are out!");
        }
    }

    public static HashMap<ClientThread, String> clientMap = new HashMap<>();

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try
        {

            ServerSocket serverSocket = new ServerSocket(12345);

            while(true)
            {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client Connected!");

                System.out.print("Enter player name: ");
                var name = sc.nextLine();

                ClientThread ct = new ClientThread(clientSocket);

                clientMap.put(ct, name);

                ct.start();
            }
        } catch(Exception e)
        {
            System.out.println(e);
        }
    }
}