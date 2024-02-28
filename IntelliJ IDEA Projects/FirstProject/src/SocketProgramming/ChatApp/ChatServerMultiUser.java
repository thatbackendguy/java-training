package SocketProgramming.ChatApp;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;
import java.util.Scanner;

class ClientReadServer extends Thread
{
    private static volatile boolean f = false;

    private PrintWriter writeClient;

    private final Socket socket;

    ClientReadServer(Socket socket)
    {
        this.socket = socket;
    }

    public static void changeFlag()
    {
        f = true;
    }

    public void run()
    {
        try
        {
            BufferedReader readClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeClient = new PrintWriter(socket.getOutputStream(), true);
            String receiveMessage;

            while(true)
            {
                if(f)
                    break;

                if((receiveMessage = readClient.readLine()) != null)
                {

                    ChatServerMultiUser.Broadcast((Thread.currentThread().getName() + " : " + receiveMessage), this);

                    if(receiveMessage.equals("exit"))
                    {
                        ChatServerMultiUser.userList.remove(this);

                        System.out.println("Current user count: " + ChatServerMultiUser.userList.size());

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
        writeClient.println(message);
    }
}

public class ChatServerMultiUser
{
    static List<ClientReadServer> userList = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {


        try(ServerSocket serverSocket = new ServerSocket(12345))
        {
            System.out.println("Server started at port: " + 12345);
            int count = -1;

            while(true)
            {

                Socket socket = serverSocket.accept();

                ClientReadServer clientReadServer = new ClientReadServer(socket);

                userList.add(clientReadServer);

                System.out.println("Current user count: " + userList.size());

                clientReadServer.setName("User" + (++count));


                clientReadServer.start();

            }


        } catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void Broadcast(String message, ClientReadServer messengerUser)
    {
        for(var user : userList)
        {
            if(user != messengerUser)
                user.sendMessage(message);
        }
    }
}
