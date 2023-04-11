package Ecommerce;

import Ecommerce.constant.StringConstant;
import Ecommerce.services.ProductService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CustomerHandler extends Thread
{
    private Socket socket;

    public CustomerHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
//            System.out.println("Request: "+reader.readLine());

            JSONObject request = new JSONObject(reader.readLine());

            switch (request.get("Operation").toString())
            {
                case "show all product" ->
                {
                    var response = new ProductService().getAllProduct();

                    printWriter.println(response);
                }
                case "buy product" ->
                {
                    var response = new ProductService().buyProduct(Integer.parseInt(request.get(StringConstant.PRODUCT_ID.constant).toString()),
                            Integer.parseInt(request.get(StringConstant.PRODUCT_QUANTITY.constant).toString()));

                    printWriter.println(response);
                }
                default ->
                {
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
