package Ecommerce;

import Ecommerce.constant.IntegerConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.controller.MenuController;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BootstrapCustomer
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Connection established...");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                MenuController.getAllProduct();

                System.out.print("Enter productID to buy: ");

                var choice = reader.readLine();

                System.out.print("Enter quantity: ");

                var quantity = reader.readLine();

                var request = new JSONObject();

                request.put(StringConstant.OPERATION.constant, "buy product");

                request.put(StringConstant.PRODUCT_ID.constant, choice);

                request.put(StringConstant.PRODUCT_QUANTITY.constant, quantity);

                var socket = new Socket("localhost", IntegerConstant.PORT.constant);

                var printWriter = new PrintWriter(socket.getOutputStream(), true);

                printWriter.println(request);

                var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("Response: "+new JSONObject(serverReader.readLine()));
            }
        } catch (Exception exception)
        {
            System.out.println("Internal error");

            exception.printStackTrace();
        }
    }
}
