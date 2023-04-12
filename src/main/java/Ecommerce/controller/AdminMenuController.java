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

// i have used AdminMenuController to increase code readability
// all the request to server and response from server will be shown here.
public class AdminMenuController
{
    public static void showAllProduct() throws ConnectException
    {
        try (Socket socket = new Socket(StringConstant.IP.getConstant().toString(), 6001);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

            var request = new JSONObject();

            request.put(StringConstant.OPERATION.getConstant().toString(), StringConstant.SHOW_ALL_PRODUCT.getConstant().toString());

            printWriter.println(request);

            var response = new JSONObject(serverReader.readLine());

            var productList = (JSONArray) response.get("Product List");

            System.out.println("ID | Product Name | Quantity | Price");

            for (Object product : productList)
            {
                var object = new JSONObject(product.toString());

                System.out.println(object.get("productID") + ", " +
                        object.get("productName") + ", " +
                        object.get("quantity") + ", " +
                        object.get("price"));
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

    public static void addNewProduct(BufferedReader reader) throws ConnectException
    {
        try (Socket socket = new Socket(StringConstant.IP.getConstant().toString(), 6001);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            socket.setSoTimeout(Integer.parseInt(StringConstant.REQUEST_TIME_OUT.getConstant().toString()));

            System.out.print("Product Name: ");

            var productName = reader.readLine();

            System.out.print("MRP: ");

            var mrp = Float.parseFloat(reader.readLine());

            System.out.print("Quantity: ");

            var quantity = Integer.parseInt(reader.readLine());

            var request = new JSONObject();

            request.put(StringConstant.OPERATION.getConstant().toString(), StringConstant.ADD_NEW_PRODUCT.getConstant().toString());

            request.put(StringConstant.PRODUCT_NAME.getConstant().toString(), productName);

            request.put(StringConstant.MRP.getConstant().toString(), mrp);

            request.put(StringConstant.QUANTITY.getConstant().toString(), quantity);

            printWriter.println(request);

            var response = new JSONObject(serverReader.readLine());

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

            request.put(StringConstant.USER_TYPE.getConstant().toString(), StringConstant.ADMIN.getConstant().toString());

            printWriter.println(request);

            var serverResponse = serverReader.readLine();

            if (serverResponse == null)
            {
                throw new ConnectException();
            }

            var response = new JSONObject(serverResponse);

            var list =  (JSONArray) response.get(StringConstant.TRANSACTION.getConstant().toString());

            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant());

            for (Object transactionList : list)
            {
                for (Object transaction : ((JSONArray) transactionList))
                {
                    System.out.println(StringConstant.PRODUCT_NAME.getConstant().toString()+": "+
                            ((JSONObject)transaction).get(StringConstant.PRODUCTNAME.getConstant().toString()).toString()+", "+
                            StringConstant.QUANTITY.getConstant().toString()+": "+
                            ((JSONObject)transaction).get(StringConstant.QUANTITY.getConstant().toString()).toString()+", "+
                            StringConstant.MRP.getConstant().toString()+": "+
                            ((JSONObject)transaction).get(StringConstant.TOTAL_AMOUNT.getConstant().toString()).toString());
                }
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
