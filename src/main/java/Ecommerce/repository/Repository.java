package Ecommerce.repository;

public interface Repository
{
    public boolean insert();

    public boolean update();

    public <E> E select();
}
