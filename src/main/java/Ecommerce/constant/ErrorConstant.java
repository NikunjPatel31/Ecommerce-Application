package Ecommerce.constant;

public enum ErrorConstant
{
    USER_DOES_NOT_EXISTS("User does not exists"),
    INVALID_CREDENTIAL("Invalid credentials"),
    INVALID_CHOICE("Invalid choice"),
    RESPONSE_TIME_OUT("Time out error"),
    SERVER_UNREACHABLE("Server unreachable"),
    INTERNAL_ERROR("Internal error"),
    CLIENT_DISCONNECTED("Client disconnected");
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
