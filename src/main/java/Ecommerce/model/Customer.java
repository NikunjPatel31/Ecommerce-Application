package Ecommerce.model;

public class Customer
{
    private int customerID;
    private String customerName;
    private String password;

    public Customer() {}

    public Customer(int customerID, String customerName, String password)
    {
        this.customerID = customerID;
        this.customerName = customerName;
        this.password = password;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getPassword()
    {
        return password;
    }
}
