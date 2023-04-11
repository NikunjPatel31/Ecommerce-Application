package Ecommerce;

import java.net.ServerSocket;
import java.net.Socket;

public class BootstrapServer
{
    public static void main(String[] args)
    {
        try
        {
            Server.start();
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
