package Ecommerce.repository;

public interface Repository
{
    void insert(Object object);

    boolean update(Object id, Object object);

    Object select();
}
