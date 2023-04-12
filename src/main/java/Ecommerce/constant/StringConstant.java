package Ecommerce.constant;

public enum StringConstant
{
    OPERATION("Operation"),

    PRODUCT_ID("ProductID"),

    RESPONSE_STATUS("status"),

    RESPONSE_STATUS_OK("ok"),

    RESPONSE_STATUS_ERROR("error"),

    RESPONSE_MESSAGE("Message"),

    PRODUCT_QUANTITY("Product quantity"),

    CUSTOMER_ID("CustomerID"),

    PASSWORD("Password"),

    OPERATION_LOGIN("login"),

    LOGIN_SUCCESSFUL("Login successful"),

    IP("10.20.40.197"),

    SHOW_ALL_PRODUCT("show all product"),

    ASTERISK_SEQ("*************************************************"),

    UNDERSCORE_SEQ( "-------------------------------------------------"),

    NEW_LINE_CHARACTER("\n"),

    PRODUCT_NAME("Product Name"),

    MRP("mrp"),

    QUANTITY("quantity"),

    ADD_NEW_PRODUCT("Add new product"),

    REQUEST_TIME_OUT(5000),

    SHOW_TRANSACTION("show transaction"),

    TRANSACTION("Transaction"),

    TOTAL_AMOUNT("totalAmount"),

    PRODUCTNAME("productName"),

    USER_TYPE("user type"),

    ADMIN("Admin"),

    CUSTOMER("Customer"),

    PORT(6001);

    private final Object constant;

    StringConstant(Object constant)
    {
        this.constant = constant;
    }

    public Object getConstant()
    {
        return constant;
    }
}
