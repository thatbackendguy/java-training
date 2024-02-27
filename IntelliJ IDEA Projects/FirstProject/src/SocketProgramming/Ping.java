package SocketProgramming;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Ping
{
    public static void main(String[] args)
    {
        try
        {
            String hostAddress = "10.20.40.100";

            InetAddress host = InetAddress.getByName(hostAddress);

            boolean reachable = host.isReachable(2000);

            if(reachable)
            {
                System.out.println(hostAddress + " is UP");
            }
            else
            {
                System.out.println(hostAddress + " is DOWN");
            }
        } catch(UnknownHostException uHE)
        {
            System.out.println(uHE);
        } catch(IOException ioE)
        {
            System.out.println(ioE);
        }
    }
}
