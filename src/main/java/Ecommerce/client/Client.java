package Ecommerce.client;

import Ecommerce.controller.ClientMenuController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client
{
    public static void main(String[] args)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
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

                    System.out.println("2. Exit");

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
                                isLoggedIn = false;
                        default ->
                                System.out.println("Invalid choice");
                    }
                }
            }
        } catch (Exception exception)
        {
            System.out.println("Internal error");

            exception.printStackTrace();
        }
    }
}
