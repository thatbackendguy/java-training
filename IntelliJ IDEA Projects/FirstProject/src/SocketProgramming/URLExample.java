package SocketProgramming;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExample
{
    public static void main(String[] args) throws Exception
    {

        URL url = new URL("https://www.motadata.com/");

        System.out.println(url.getAuthority());
        System.out.println(url.getHost());
        System.out.println(url.getFile());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getProtocol());
        System.out.println(url.getDefaultPort());
        System.out.println(url.getUserInfo());
        System.out.println(url.getContent());
    }
}