package Ecommerce.repository;

import Ecommerce.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationRepo implements Repository
{
    private static final Map<Integer, Customer> authentication = new HashMap<>();

    private static AuthenticationRepo instance;

    private AuthenticationRepo() {}

    public static AuthenticationRepo getInstance()
    {
        if (instance == null)
            instance = new AuthenticationRepo();

        return instance;
    }

    @Override
    public void insert(Object object)
    {
        authentication.computeIfAbsent(((Customer) object).getCustomerID(), k-> ((Customer) object));
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

    public Object select(int customerID)
    {
        return authentication.get(customerID);
    }
}
