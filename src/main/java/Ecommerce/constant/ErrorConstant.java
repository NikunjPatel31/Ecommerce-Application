package Ecommerce.constant;

public enum ErrorConstant
{
    USER_DOES_NOT_EXISTS("User does not exists"),
    INVALID_CREDENTIAL("Invalid credentials"),
    INVALID_CHOICE("Invalid choice"),
    INTERNAL_ERROR("Internal error");
    private final Object constant;

    ErrorConstant(Object constant)
    {
        this.constant = constant;
    }

    public Object getConstant()
    {
        return constant;
    }
}
