package SocketProgramming;

import java.net.*;

public class ProxyDemo
{
    public static void main(String[] args)
    {
        try
        {
            // Set the proxy server address and port
            String proxyAddress = "1.1.1.1";
            int proxyPort = 80;

            // Set the server address and port
            String serverAddress = "google.com";
            int serverPort = 80;

            // Create a new Proxy instance with the specified type and address
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));

            // Create a new URL instance for the server
            URL url = new URL("http://" + serverAddress + ":" + serverPort);

            // Open a connection to the server through the proxy
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

            // Set the request method
            connection.setRequestMethod("GET");

            // Print the response code
            System.out.println("Response code: " + connection.getResponseCode());

            // Close the connection
            connection.disconnect();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
