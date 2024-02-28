package SocketProgramming.TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            System.out.println("Server IP Address: " + serverAddress.getHostAddress());

            Socket socket = new Socket("localhost", 12345);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(input.readLine());

            out.println("Hello Server! <sent from Client.java>");

            input.close();

            out.close();

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
    }
}
