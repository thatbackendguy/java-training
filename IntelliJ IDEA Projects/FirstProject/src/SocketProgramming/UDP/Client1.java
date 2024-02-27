package SocketProgramming.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client1
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];

        boolean stop = false;

        while(!stop)
        {
            String strData = "Client1 text message!";

            sendData = strData.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9090);

            clientSocket.send(sendPacket);
        }
        clientSocket.close();
    }
}
