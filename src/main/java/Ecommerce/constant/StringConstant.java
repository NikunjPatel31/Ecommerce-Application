package Ecommerce.constant;

import Ecommerce.model.Product;

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

    LOCALHOST("localhost"),

    SHOW_ALL_PRODUCT("show all product"),

    ASTERISK_SEQ("*************************************************"),

    UNDERSCORE_SEQ( "-------------------------------------------------"),

    NEW_LINE_CHARACTER("\n"),

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
