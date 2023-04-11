package Ecommerce;

import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void start()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(6001);

            while (!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();

                System.out.println("Connected user: "+socket.getRemoteSocketAddress());

                new CustomerHandler(socket).start();
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
