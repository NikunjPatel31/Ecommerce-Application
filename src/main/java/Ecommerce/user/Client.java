package Ecommerce.user;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.controller.ClientMenuController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;

public class Client
{
    public static void main(String[] args)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            // isLoggedIn flag is used because once user logs in we don't want to show them
            // login and create account options hence we used flag to bypass.
            var isLoggedIn = false;

            while (true)
            {
                if (!isLoggedIn)
                {
                    isLoggedIn = ClientMenuController.authentication(reader);
                }
                else
                {
                    System.out.println("1. Show Product");

                    System.out.println("2. Show Transaction");

                    System.out.println("3. Exit");

                    System.out.print("Choice: ");

                    var choice = reader.readLine();

                    switch (choice)
                    {
                        case "1" ->
                        {
                            ClientMenuController.getAllProduct();

                            ClientMenuController.buyProduct(reader);
                        }
                        case "2" ->
                                ClientMenuController.showTransaction();
                        case "3" ->
                                isLoggedIn = false;
                        default ->
                                System.out.println(ErrorConstant.INVALID_CHOICE.getConstant().toString());
                    }
                }
            }
        }
        catch (ConnectException connectException)
        {
            System.out.println(StringConstant.UNDERSCORE_SEQ.getConstant().toString()+
                    StringConstant.NEW_LINE_CHARACTER.getConstant().toString()+
                    ErrorConstant.SERVER_UNREACHABLE.getConstant().toString()+
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
