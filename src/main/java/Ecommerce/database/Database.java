package Ecommerce.database;

public interface Database
{
    public boolean insert();

    public boolean update();

    public <E> E select();
}

