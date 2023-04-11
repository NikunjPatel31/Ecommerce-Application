package Ecommerce.controller;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.services.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminMenuController
{
    public static void showAllProduct()
    {
        try (Socket socket = new Socket(StringConstant.LOCALHOST.getConstant().toString(), 6001);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
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
        catch (Exception exception)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.INTERNAL_ERROR.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
        }
    }

    public static void addNewProduct(BufferedReader reader)
    {
        try (Socket socket = new Socket(StringConstant.LOCALHOST.getConstant().toString(), 6001);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            System.out.print("Product Name: ");

            var productName = reader.readLine();

            System.out.print("MRP: ");

            var mrp = Float.parseFloat(reader.readLine());

            System.out.print("Quantity: ");

            var quantity = Integer.parseInt(reader.readLine());
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
}
