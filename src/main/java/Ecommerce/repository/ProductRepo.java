package Ecommerce.repository;

import Ecommerce.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRepo implements Repository
{
    private final Map<Integer, Product> productMap = new HashMap<>();

    private static ProductRepo instance;

    private ProductRepo() {
        productMap.put(1, new Product("iPhone", 1, 10, 75000));
        productMap.put(2, new Product("One plus nord", 2, 7, 45000));
        productMap.put(3, new Product("Washing machine", 3, 14, 25000));
        productMap.put(4, new Product("AC", 4, 12, 22000));
        productMap.put(5, new Product("Revolving chair", 5, 10, 14000));
    }
    public static ProductRepo getInstance()
    {
        if (instance == null)
            instance = new ProductRepo();

        return instance;
    }

    @Override
    public synchronized void insert(Object object)
    {
        productMap.computeIfAbsent(((Product) object).getProductID(), k -> ((Product) object));
    }

    @Override
    public boolean update(Object id, Object object)
    {
        return false;
    }

    public void update(int productID, int quantity)
    {
        productMap.get(productID).setQuantity(productMap.get(productID).getQuantity() - quantity);
    }

    @Override
    public Object select()
    {
        return productMap.keySet().stream().map(productMap::get).collect(Collectors.toList());
    }

    public Object select(int productID)
    {
        return productMap.get(productID);
    }
}
