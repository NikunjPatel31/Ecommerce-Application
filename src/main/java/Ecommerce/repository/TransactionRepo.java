package Ecommerce.repository;

import Ecommerce.model.Customer;
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
        transaction.computeIfAbsent(((Customer) object).getCustomerID(), k -> new ArrayList<>());
    }

    public void insert(Customer customer, Product product, int quantity)
    {
        insert(customer);

        var list = transaction.get(customer.getCustomerID());

        list.add(new Transaction(product.getPrice(), quantity, product.getProductName()));

        transaction.put(customer.getCustomerID(), list);
    }

    @Override
    public boolean update()
    {
        return false;
    }

    @Override
    public Object select()
    {
        return null;
    }
}
