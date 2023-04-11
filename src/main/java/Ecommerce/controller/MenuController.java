package Ecommerce.controller;

import Ecommerce.constant.IntegerConstant;
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
        try (Socket socket = new Socket("localhost", IntegerConstant.PORT.constant);
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            var request = new JSONObject();

            request.put("Operation", "show all product");

            printWriter.println(request);

            var response = new JSONObject(serverReader.readLine());

            var jsonArray = (JSONArray) response.get("Product List");

            for (Object o : jsonArray)
            {
                var object = new JSONObject(o.toString());

                System.out.println(object.get("productID") + ", " +
                        object.get("productName") + ", " +
                        object.get("quantity") + ", " +
                        object.get("price"));
            }
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
