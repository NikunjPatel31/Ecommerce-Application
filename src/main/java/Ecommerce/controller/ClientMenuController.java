package Ecommerce.controller;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.regex.Pattern;

public class ClientMenuController
{
    static int customerID = -1;
    public static boolean authentication(BufferedReader reader) throws ConnectException
    {
        try (Socket socket = new Socket(StringConstant.IP.getConstant().toString(), Integer.parseInt(StringConstant.PORT.getConstant().toString()));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

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

                    var serverResponse = serverReader.readLine();

                    if (serverResponse == null)
                    {
                        throw new ConnectException();
                    }

                    var response = new JSONObject(serverResponse);

                    System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                            StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                            response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString())+
                            StringConstant.NEW_LINE_CHARACTER.getConstant()+
                            StringConstant.UNDERSCORE_SEQ.getConstant());

                    if (response.get(StringConstant.RESPONSE_STATUS.getConstant().toString()).toString().equals(StringConstant.RESPONSE_STATUS_OK.getConstant().toString()))
                    {
                        customerID = Integer.parseInt(response.get(StringConstant.CUSTOMER_ID.getConstant().toString()).toString());

                        return true;
                    }
                    return false;
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

                    var serverResponse = serverReader.readLine();

                    if (serverResponse == null)
                    {
                        throw new ConnectException();
                    }

                    var response = new JSONObject(serverResponse);

                    System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                            response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString()));

                    System.out.println(StringConstant.CUSTOMER_ID.getConstant()+": "+response.get(StringConstant.CUSTOMER_ID.getConstant().toString())+
                            StringConstant.NEW_LINE_CHARACTER.getConstant()+
                            StringConstant.UNDERSCORE_SEQ.getConstant());

                    if (response.get(StringConstant.RESPONSE_STATUS.getConstant().toString()).toString().equals(StringConstant.RESPONSE_STATUS_OK.getConstant().toString()))
                    {
                        customerID = Integer.parseInt(response.get(StringConstant.CUSTOMER_ID.getConstant().toString()).toString());

                        return true;
                    }
                    return false;
                }
                default ->
                        System.out.println("Invalid choice");
            }
        }
        catch (SocketTimeoutException socketTimeoutException)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.RESPONSE_TIME_OUT.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
        catch (ConnectException connectException)
        {
            // exception needs to get propogate because if we don't then program will
            // go into infinite loop
            throw connectException;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();

            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.INTERNAL_ERROR.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }

        return false;
    }
    public static void getAllProduct() throws ConnectException
    {
        try (Socket socket = new Socket(StringConstant.IP.getConstant().toString(), Integer.parseInt(StringConstant.PORT.getConstant().toString()));
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

            var request = new JSONObject();

            request.put(StringConstant.OPERATION.getConstant().toString(), StringConstant.SHOW_ALL_PRODUCT.getConstant().toString());

            printWriter.println(request);

            var serverResponse = serverReader.readLine();

            if (serverResponse == null)
            {
                throw new ConnectException();
            }

            var response = new JSONObject(serverResponse);

            var productList = (JSONArray) response.get("Product List");

            System.out.println("ID | Product Name | Quantity | Price");

            for (Object product : productList)
            {
                var object = new JSONObject(product.toString());

                System.out.println("ID: "+object.get("productID") + ", " +
                        "Name: "+ object.get("productName") + ", " +
                        "Quantity: "+ object.get("quantity") + ", " +
                        "Price: "+ object.get("price"));
            }
        }
        catch (ConnectException connectException)
        {
            // exception needs to get propogate because if we don't then program will
            // go into infinite loop
            throw connectException;
        }
        catch (Exception exception)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.INTERNAL_ERROR.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
    }
    public static void buyProduct(BufferedReader reader) throws ConnectException
    {
        try (var socket = new Socket(StringConstant.IP.getConstant().toString(), Integer.parseInt(StringConstant.PORT.getConstant().toString()));
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

            var request = new JSONObject();

            System.out.print("Enter productID to buy: ");

            var value = "";
            while ((value = reader.readLine()).isBlank()
                    || !Pattern.matches("\\d+", value))
            {
                System.out.print("Enter valid productID: ");
            }

            request.put(StringConstant.PRODUCT_ID.getConstant().toString(), value);

            System.out.print("Enter quantity: ");

            while ((value = reader.readLine()).isBlank()
                    || !Pattern.matches("\\d+", value))
            {
                System.out.print("Enter valid quantity: ");
            }

            request.put(StringConstant.OPERATION.getConstant().toString(), "buy product");

            request.put(StringConstant.PRODUCT_QUANTITY.getConstant().toString(), value);

            request.put(StringConstant.CUSTOMER_ID.getConstant().toString(), customerID);

            printWriter.println(request);

            var serverResponse = serverReader.readLine();

            if (serverResponse == null)
            {
                throw new ConnectException();
            }

            var response = new JSONObject(serverResponse);

            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    response.get(StringConstant.RESPONSE_MESSAGE.getConstant().toString())+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
        catch (ConnectException connectException)
        {
            // exception needs to get propogate because if we don't then program will
            // go into infinite loop
            throw connectException;
        }
        catch (Exception exception)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.INTERNAL_ERROR.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
    }
    public static void showTransaction() throws ConnectException
    {
        try (var socket = new Socket(StringConstant.IP.getConstant().toString(), Integer.parseInt(StringConstant.PORT.getConstant().toString()));
             var serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

            var request = new JSONObject();

            request.put(StringConstant.OPERATION.getConstant().toString(), StringConstant.SHOW_TRANSACTION.getConstant().toString());

            request.put(StringConstant.CUSTOMER_ID.getConstant().toString(), customerID);

            request.put(StringConstant.USER_TYPE.getConstant().toString(), StringConstant.CUSTOMER.getConstant().toString());

            printWriter.println(request);

            var serverResponse = serverReader.readLine();

            if (serverResponse == null)
            {
                throw new ConnectException();
            }

            var response = new JSONObject(serverResponse);

            var list =  (JSONArray) response.get(StringConstant.TRANSACTION.getConstant().toString());

            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant());

            for (Object transaction : list)
            {
                var object = new JSONObject(transaction.toString());

                System.out.println(StringConstant.PRODUCT_NAME.getConstant().toString()+": "+
                        object.get(StringConstant.PRODUCTNAME.getConstant().toString()).toString()+", "+
                        StringConstant.QUANTITY.getConstant().toString()+": "+
                        object.get(StringConstant.QUANTITY.getConstant().toString()).toString()+", "+
                        StringConstant.MRP.getConstant().toString()+": "+
                        object.get(StringConstant.TOTAL_AMOUNT.getConstant().toString()).toString());
            }

            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant());
        }
        catch (ConnectException connectException)
        {
            // exception needs to get propogate because if we don't then program will
            // go into infinite loop
            throw connectException;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.INTERNAL_ERROR.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
    }
}
