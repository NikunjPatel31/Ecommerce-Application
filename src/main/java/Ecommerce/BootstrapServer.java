package Ecommerce;

import Ecommerce.server.Server;

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
