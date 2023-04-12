package Ecommerce.Utility;

public class Utility
{
    private static int productID = 10;

    private static int customerID = 10001;

    public static int incrementAndGetCustomerID()
    {
        return ++customerID;
    }

    public static int incrementAndGetProductID() { return ++productID;}
}
