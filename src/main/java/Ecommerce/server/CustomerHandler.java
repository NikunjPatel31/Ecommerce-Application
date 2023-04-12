package Ecommerce.server;

import Ecommerce.Utility.Utility;
import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.model.Customer;
import Ecommerce.model.Product;
import Ecommerce.services.AuthenticationService;
import Ecommerce.services.ProductService;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CustomerHandler extends Thread
{
    private final Socket socket;

    private final ProductService productService;

    private final AuthenticationService authenticationService;

    public CustomerHandler(Socket socket, ProductService productService, AuthenticationService authenticationService)
    {
        this.socket = socket;

        this.productService = productService;

        this.authenticationService = authenticationService;
    }

    @Override
    public void run()
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            var clientRequest = reader.readLine();

            if (clientRequest == null)
            {
                throw new RuntimeException("Client disconnected");
            }

            var request = new JSONObject(clientRequest);

            switch (request.get("Operation").toString())
            {
                case "create account" ->
                {
                    var response = authenticationService.createAccount(new Customer(Utility.incrementAndGetCustomerID(),
                            request.get("Name").toString(),
                            request.get("Password").toString()));

                    printWriter.println(response);
                }
                case "login" ->
                {
                    var response = authenticationService.login(Integer.parseInt(request.get(StringConstant.CUSTOMER_ID.getConstant().toString()).toString()), request.get(StringConstant.PASSWORD.getConstant().toString()).toString());

                    printWriter.println(response);
                }
                case "show all product" ->
                {
                    var response = productService.showAllProduct();

                    printWriter.println(response);
                }
                case "buy product" ->
                {
                    var response = productService.buyProduct(Integer.parseInt(request.get(StringConstant.PRODUCT_ID.getConstant().toString()).toString()),
                            Integer.parseInt(request.get(StringConstant.PRODUCT_QUANTITY.getConstant().toString()).toString()),
                            Integer.parseInt(request.get(StringConstant.CUSTOMER_ID.getConstant().toString()).toString()));

                    printWriter.println(response);
                }
                case "Add new product" ->
                {
                    var response = productService.addNewProduct(new Product(request.get(StringConstant.PRODUCT_NAME.getConstant().toString()).toString(),
                            Utility.incrementAndGetProductID(),
                            Integer.parseInt(request.get(StringConstant.QUANTITY.getConstant().toString()).toString()),
                            Float.parseFloat(request.get(StringConstant.MRP.getConstant().toString()).toString())));

                    printWriter.println(response);
                }
                default -> System.out.println("Invalid choice");
            }
        }
        catch (RuntimeException runtimeException)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.CLIENT_DISCONNECTED.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant()+
                    StringConstant.UNDERSCORE_SEQ.getConstant());
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
