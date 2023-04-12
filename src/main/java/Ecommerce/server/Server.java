package Ecommerce.server;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.services.AuthenticationService;
import Ecommerce.services.ProductService;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    public static void start()
    {
        try (ServerSocket serverSocket = new ServerSocket(6001))
        {
            while (!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();

                System.out.println("Connected user: "+socket.getRemoteSocketAddress());

                ExecutorService executorService = Executors.newFixedThreadPool(1);

                executorService.execute(new CustomerHandler(socket, new ProductService(), new AuthenticationService()));
            }
        }
        catch (Exception exception)
        {
            System.out.println(ErrorConstant.INTERNAL_ERROR.getConstant());
        }
    }
}
