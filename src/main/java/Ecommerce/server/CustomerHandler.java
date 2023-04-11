package Ecommerce.server;

import Ecommerce.constant.StringConstant;
import Ecommerce.services.ProductService;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CustomerHandler extends Thread
{

    private final Socket socket;

    private ProductService productService;

    public CustomerHandler(Socket socket, ProductService productService)
    {
        this.socket = socket;

        this.productService = productService;
    }

    @Override
    public void run()
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
//            System.out.println("Request: "+reader.readLine());

            JSONObject request = new JSONObject(reader.readLine());


            switch (request.getString("Operation"))
            {
                case "show all product" ->
                {
                    var response = productService.getAllProduct();

                    printWriter.println(response);
                }
                case "buy product" ->
                {
                    var response = productService.buyProduct(Integer.parseInt(request.get(StringConstant.PRODUCT_ID.getConstant().toString()).toString()),
                            Integer.parseInt(request.get(StringConstant.PRODUCT_QUANTITY.getConstant().toString()).toString()));

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
