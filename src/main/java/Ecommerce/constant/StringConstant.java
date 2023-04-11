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
