package Ecommerce.client;

import Ecommerce.controller.MenuController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Customer
{
    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                MenuController.getAllProduct();

                MenuController.buyProduct(reader);
            }
        } catch (Exception exception)
        {
            System.out.println("Internal error");

            exception.printStackTrace();
        }
    }
}
