package SocketProgramming.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            DatagramSocket clientSocket = new DatagramSocket(0);
            //            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(5000);
            // datagram packets are only bytes
            byte[] sendData = new byte[1024]; // 1kb
            byte[] receiveData = new byte[1024]; // 1kb
            // amount of data =  65535 - 20 (Heeader) - 8 (UDP Header) = 65508

            String stringSendData = "Hello Server";
            sendData = stringSendData.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9090);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            receiveData = receivePacket.getData();

            String strReceiveData = new String(receiveData);

            System.out.println("FROM SERVER: " + strReceiveData);

            clientSocket.close();
        } catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
