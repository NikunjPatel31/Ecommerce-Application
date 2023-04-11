package Ecommerce.model;

public class Transaction
{
    private float mrp;
    private int quantity;
    private String productName;

    public Transaction() {}

    public Transaction(float mrp, int quantity, String productName)
    {
        this.mrp = mrp;
        this.quantity = quantity;
        this.productName = productName;
    }

    public void setTotalAmount(float mrp)
    {
        this.mrp = mrp;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public float getTotalAmount()
    {
        return mrp;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getProductName()
    {
        return productName;
    }
}
