package Ecommerce.repository;

import Ecommerce.model.Product;
import Ecommerce.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepo implements Repository
{
    private static final Map<Integer, List<Transaction>> transaction = new HashMap<>();
    private static TransactionRepo instance;
    private TransactionRepo() {}

    public static TransactionRepo getInstance()
    {
        if (instance == null)
            instance = new TransactionRepo();

        return instance;
    }

    @Override
    public void insert(Object object)
    {
        transaction.computeIfAbsent(((Integer) object), k -> new ArrayList<>());
    }

    public void insert(Integer customerID, Product product, int quantity)
    {
        insert(customerID);

        var list = transaction.get(customerID);

        list.add(new Transaction(product.getPrice(), quantity, product.getProductName()));

        transaction.put(customerID, list);
    }

    @Override
    public boolean update(Object id, Object object)
    {
        return false;
    }

    @Override
    public Object select()
    {
        return null;
    }
}
