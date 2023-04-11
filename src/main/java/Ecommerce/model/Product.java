package Ecommerce.model;

public class Product
{
    String productName;

    int productID;

    int quantity;

    float price;

    public Product() {}

    public Product(String productName, int productID, int quantity, float price)
    {
        this.productName = productName;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getProductName()
    {
        return productName;
    }

    public int getProductID()
    {
        return productID;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public float getPrice()
    {
        return price;
    }
}
