package Ecommerce.Utility;

public class Utility
{
    private static int productID = 0;

    private static int customerID = 10001;

    public static int incrementAndGet()
    {
        return ++customerID;
    }
}
