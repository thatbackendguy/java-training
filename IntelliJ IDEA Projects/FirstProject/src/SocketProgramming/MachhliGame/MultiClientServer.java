package SocketProgramming.MachhliGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

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

                //                writer.println(sc.nextLine());
                //                Instant startTime = Instant.now();

                String clientMsg = reader.readLine();

                //                Instant endTime = Instant.now();

                //                long timeElapsed = Duration.between(startTime, endTime).toMillis();

                //                if(timeElapsed / 1000 > 5)
                //                {
                //                    MultiClientServer.counter = 0;
                //                    break;
                //                }

                if(!clientMsg.equals(""))
                {
                    if(MultiClientServer.checkMsg(clientMsg))
                        MultiClientServer.broadcast(clientMsg, this);
                    else
                        break;
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
    static List<ClientThread> clients = new ArrayList<>();

    static int counter = 0;

    public static boolean checkMsg(String message)
    {
        String[] gameTerms = {"1m", "1pmg", "1cpk", "2m", "2m", "2pmg", "2pmg", "2cpk", "2cpk", "3m", "3m", "3m", "3pmg", "3pmg", "3pmg", "3cpk", "3cpk", "3cpk", "4m", "4m", "4m", "4m", "4pmg", "4pmg", "4pmg", "4pmg", "4cpk", "4cpk", "4cpk", "4cpk", "5m", "5m", "5m", "5m", "5m", "5pmg", "5pmg", "5pmg", "5pmg", "5pmg", "5cpk", "5cpk", "5cpk", "5cpk", "5cpk", "6m", "6m", "6m", "6m", "6m", "6m", "6pmg", "6pmg", "6pmg", "6pmg", "6pmg", "6pmg", "6cpk", "6cpk", "6cpk", "6cpk", "6cpk", "6cpk", "7m", "7m", "7m", "7m", "7m", "7m", "7m", "7pmg", "7pmg", "7pmg", "7pmg", "7pmg", "7pmg", "7pmg", "7cpk", "7cpk", "7cpk", "7cpk", "7cpk", "7cpk", "7cpk", "8m", "8m", "8m", "8m", "8m", "8m", "8m", "8m", "8pmg", "8pmg", "8pmg", "8pmg", "8pmg", "8pmg", "8pmg", "8pmg", "8cpk", "8cpk", "8cpk", "8cpk", "8cpk", "8cpk", "8cpk", "8cpk", "9m", "9m", "9m", "9m", "9m", "9m", "9m", "9m", "9m", "9pmg", "9pmg", "9pmg", "9pmg", "9pmg", "9pmg", "9pmg", "9pmg", "9pmg", "9cpk", "9cpk", "9cpk", "9cpk", "9cpk", "9cpk", "9cpk", "9cpk", "9cpk", "10m", "10m", "10m", "10m", "10m", "10m", "10m", "10m", "10m", "10m", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10pmg", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "10cpk", "11m", "11m", "11m", "11m", "11m", "11m", "11m", "11m", "11m", "11m", "11m", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11pmg", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "11cpk", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12m", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12pmg", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "12cpk", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13m", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13pmg", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "13cpk", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14m", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14pmg", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "14cpk", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15m", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15pmg", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "15cpk", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16m", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16pmg", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "16cpk", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17m", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17pmg", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "17cpk", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18m", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18pmg", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "18cpk", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19m", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19pmg", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk", "19cpk"};

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
        for(var client : clients)
        {
            if(client != curr)
                client.sendMessage(message);
        }
    }

    public static void main(String[] args)
    {
        try
        {

            ServerSocket serverSocket = new ServerSocket(12345);

            while(true)
            {
                Socket clientSocket = serverSocket.accept();

                ClientThread ct = new ClientThread(clientSocket);

                ct.start();

                clients.add(ct);

                System.out.println("Client Connected!");

            }

        } catch(Exception e)
        {
            System.out.println(e);
        }
    }
}