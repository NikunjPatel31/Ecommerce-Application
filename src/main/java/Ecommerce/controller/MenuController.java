package Ecommerce.controller;

import Ecommerce.constant.StringConstant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MenuController
{
    public static void getAllProduct()
    {
        try (Socket socket = new Socket("localhost", (Integer) StringConstant.PORT.getConstant());
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            var request = new JSONObject();

            request.put("Operation", "show all product");

            printWriter.println(request);

            var response = new JSONObject(serverReader.readLine());

            var productList = (JSONArray) response.get("Product List");

            for (Object o : productList)
            {
                var object = new JSONObject(o.toString());

                System.out.println(object.get("productID") + ", " +
                        object.get("productName") + ", " +
                        object.get("quantity") + ", " +
                        object.get("price"));
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void buyProduct(BufferedReader reader)
    {
        try (var socket = new Socket("localhost", (Integer) StringConstant.PORT.getConstant());
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true);)
        {
            System.out.print("Enter productID to buy: ");

            var choice = reader.readLine();

            System.out.print("Enter quantity: ");

            var quantity = reader.readLine();

            var request = new JSONObject();

            request.put(StringConstant.OPERATION.getConstant().toString(), "buy product");

            request.put(StringConstant.PRODUCT_ID.getConstant().toString(), choice);

            request.put(StringConstant.PRODUCT_QUANTITY.getConstant().toString(), quantity);

            printWriter.println(request);

            var responseString = serverReader.readLine();

            var response = new JSONObject(responseString);

            System.out.println(response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString()));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
