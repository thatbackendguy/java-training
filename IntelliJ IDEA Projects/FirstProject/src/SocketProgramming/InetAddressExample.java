package SocketProgramming;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

public class InetAddressExample
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress address = InetAddress.getLocalHost();

            System.out.println(address);

            System.out.println(address.getHostAddress());

            System.out.println(address.isLoopbackAddress());

            System.out.println(address.getCanonicalHostName());

            System.out.println(address.getClass());

            InetAddress ad2 = InetAddress.getByName("google.com");

            System.out.println(ad2);

            System.out.println(ad2.getHostAddress());

            System.out.println(ad2.isLoopbackAddress());
    
            System.out.println(ad2.getCanonicalHostName());

            System.out.println(ad2.getClass());

        } catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
