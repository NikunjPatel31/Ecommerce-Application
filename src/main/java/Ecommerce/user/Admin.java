package Ecommerce.user;

import Ecommerce.constant.ErrorConstant;
import Ecommerce.constant.StringConstant;
import Ecommerce.controller.AdminMenuController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Admin
{
    public static void main(String[] args)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            while (true)
            {
                System.out.println("1. Show all product");

                System.out.println("2. Add new product");

                System.out.println("3. Show transaction");

                System.out.print("Choice: ");

                var choice = reader.readLine();

                switch (choice)
                {
                    case "1" ->
                            AdminMenuController.showAllProduct();
                    case "2" ->
                            AdminMenuController.addNewProduct(reader);
                    case "3" ->
                    {

                    }
                    default -> System.out.println(ErrorConstant.INVALID_CHOICE.getConstant());
                }
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
}
