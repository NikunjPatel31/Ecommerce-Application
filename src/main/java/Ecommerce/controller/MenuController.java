package Ecommerce.controller;

import Ecommerce.constant.StringConstant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class MenuController
{
    public static boolean authentication(BufferedReader reader)
    {
        try (Socket socket = new Socket("localhost", (Integer) StringConstant.PORT.getConstant());
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            System.out.println("1. Login");

            System.out.println("2. Create Account");

            System.out.print("Choice: ");

            var choice = reader.readLine();

            switch (choice)
            {
                case "1" ->
                {
                    // login
                    var request = new JSONObject();

                    System.out.print(StringConstant.CUSTOMER_ID.getConstant()+": ");

                    var value = "";
                    while ((value = reader.readLine()).isBlank()
                            || !Pattern.matches("\\d+", value))
                    {
                        System.out.print("Enter valid customerID: ");
                    }

                    request.put(StringConstant.CUSTOMER_ID.getConstant().toString(), value.trim());

                    System.out.print(StringConstant.PASSWORD.getConstant()+": ");

                    while ((value = reader.readLine()).isBlank()
                            || !Pattern.matches("[0-9a-zA-Z@#$%]{8,}", value))
                    {
                        System.out.print("Enter valid password: ");
                    }

                    request.put(StringConstant.PASSWORD.getConstant().toString(), value.trim());

                    request.put(StringConstant.OPERATION.getConstant().toString(), StringConstant.OPERATION_LOGIN.getConstant().toString());

                    printWriter.println(request);

                    var response = new JSONObject(serverReader.readLine());

                    System.out.println(response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString()));

                    return response.get(StringConstant.RESPONSE_STATUS.getConstant().toString()).toString().equals(StringConstant.RESPONSE_STATUS_OK.getConstant().toString());
                }
                case "2" ->
                {
                    // create account
                    var request = new JSONObject();

                    System.out.print("Name: ");

                    var value = "";

                    while ((value = reader.readLine()).isBlank())
                    {
                        System.out.print("Please enter proper name: ");
                    }

                    request.put("Name", value.trim());

                    System.out.print("Password: ");

                    while ((value = reader.readLine()).isBlank())
                    {
                        System.out.print("Please enter proper password: ");
                    }

                    request.put("Password", value);

                    request.put("Operation", "create account");

                    printWriter.println(request);

                    var response = new JSONObject(serverReader.readLine());

                    System.out.println(response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString()));

                    System.out.println(StringConstant.CUSTOMER_ID.getConstant()+": "+response.get(StringConstant.CUSTOMER_ID.getConstant().toString()));

                    return response.get(StringConstant.RESPONSE_STATUS.getConstant().toString()).toString().equals(StringConstant.RESPONSE_STATUS_OK.getConstant().toString());
                }
                default ->
                {
                    System.out.println("Invalid choice");
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return false;
    }
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
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
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
