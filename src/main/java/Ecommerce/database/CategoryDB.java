package Ecommerce.database;

public class CategoryDB implements Database
{
    public CategoryDB instance;

    private CategoryDB()
    {
    }

    public CategoryDB getInstance()
    {
        if (instance == null)
            instance = new CategoryDB();

        return instance;
    }

    @Override
    public boolean insert()
    {
        return false;
    }

    @Override
    public boolean update()
    {
        return false;
    }

    @Override
    public <E> E select()
    {
        return null;
    }
}
